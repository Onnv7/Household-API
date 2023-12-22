package com.onnv.household.service;

import com.onnv.household.constants.ErrorConstant;
import com.onnv.household.dto.request.AcceptOrderBillRequest;
import com.onnv.household.dto.request.AddOrderStatusRequest;
import com.onnv.household.dto.request.CreateOrderBillRequest;
import com.onnv.household.dto.request.RefuseOrderBillByIdRequest;
import com.onnv.household.dto.response.GetOrderBillDetailsByIdResponse;
import com.onnv.household.dto.sql.GetAllOrderBillByStatus;
import com.onnv.household.dto.sql.GetAllOrderBillByUserIdAndStatus;
import com.onnv.household.entity.*;
import com.onnv.household.enums.ItemOrderStatus;
import com.onnv.household.enums.OrderStatus;
import com.onnv.household.enums.TransactionStatus;
import com.onnv.household.model.CustomException;
import com.onnv.household.repository.OrderBillRepository;
import com.onnv.household.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderBillService {
    private final OrderBillRepository orderBillRepository;
    private final UserService userService;
    private final ShipmentDetailsService shipmentDetailsService;
    private final ProductClassificationService productClassificationService;
    private final ProductService productService;
    private final ItemOrderService itemOrderService;
    private final OrderStatusService orderStatusService;
    private final ModelMapperUtils modelMapperUtils;

    public OrderBillEntity getOrderBillById(String id) {
        return orderBillRepository.findById(id).orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND + " - " + id));
    }

    // ================================================
    @Transactional
    public void createOrderBill(CreateOrderBillRequest data, String userId) {
        UserEntity user = userService.getUserById(userId);
        OrderBillEntity orderBill = new OrderBillEntity();
        orderBill.setUser(user);

        ShipmentDetailsEntity shipmentDetails = modelMapperUtils.mapClass(data.getShipmentDetails(), ShipmentDetailsEntity.class);
        shipmentDetails = shipmentDetailsService.createShipmentDetails(shipmentDetails);
        orderBill.setShipmentDetails(shipmentDetails);
        orderBill.setTotalProductPrice(0.0);
        orderBill.setNote(data.getNote());


        List<CreateOrderBillRequest.ItemOrder> itemOrders = data.getItemOrderList();

        List<ItemOrderEntity> itemOrderList = itemOrders.stream().map(it -> {
            ProductEntity product = productService.getProductById(it.getProductId());
            ItemOrderEntity itemOrder = modelMapperUtils.mapNotNullClass(it, ItemOrderEntity.class);
            itemOrder.setOrderBill(orderBill);
            // TODO kiem tra lai price = null va classification = 0
            if (it.getTypeName() != null) {
                itemOrder.setTypeName(it.getTypeName());
                itemOrder.setPrice(productClassificationService.getPriceOfProductType(product.getId(), it.getTypeName()));
            } else {
                itemOrder.setPrice(product.getPrice());
            }
//            itemOrder.setStatus(ItemOrderStatus.ACCEPT);
            itemOrder.setProduct(product);

            return itemOrder;
        }).collect(Collectors.toCollection(ArrayList::new));

        orderBill.setItemOrderList(itemOrderList);

        Double totalBill = itemOrderList.stream().mapToDouble(itemOrder -> itemOrder.getPrice() * itemOrder.getQuantity()).sum();
        orderBill.setTotalProductPrice(totalBill);

        OrderStatusEntity orderStatus = OrderStatusEntity.builder()
                .orderBill(orderBill)
                .status(OrderStatus.CREATED)
                .build();

        List<OrderStatusEntity> orderStatusList = new ArrayList<>(Arrays.asList(orderStatus));

        orderBill.setOrderStatusList(orderStatusList);
        orderBillRepository.save(orderBill);
    }

    @Transactional
    public GetOrderBillDetailsByIdResponse getOrderBillDetailsById(String orderBillId) {
        OrderBillEntity orderBil = getOrderBillById(orderBillId);
        GetOrderBillDetailsByIdResponse resData = modelMapperUtils.mapNotNullClass(orderBil, GetOrderBillDetailsByIdResponse.class);

        resData.setItemOrderList(modelMapperUtils.mapList(orderBillRepository.getItemOrderListByOrderBillId(orderBillId), GetOrderBillDetailsByIdResponse.ItemOrder.class));
        return resData;
    }

    public void addOrderStatusForBill(String orderBillId, AddOrderStatusRequest body) {
        OrderBillEntity orderBill = getOrderBillById(orderBillId);
        OrderStatus lastStatus = orderBill.getOrderStatusList().get(orderBill.getOrderStatusList().size() - 1).getStatus();
        if(lastStatus == OrderStatus.CREATED) {
            throw new CustomException(ErrorConstant.ORDER_BILL_STATUS_INVALID);
        }
        OrderStatusEntity orderStatus = modelMapperUtils.mapNotNullClass(body, OrderStatusEntity.class);
        orderStatus.setOrderBill(orderBill);
        orderStatusService.createOrderStatus(orderStatus);
    }

    public List<GetAllOrderBillByStatus> getAllOrderBillByStatus(OrderStatus orderStatus) {
        return orderBillRepository.getAllOrderBillByStatus(orderStatus.toString());
    }

    @Transactional
    public void acceptOrderBillById(String orderBillId, AcceptOrderBillRequest body) {
        OrderBillEntity orderBill = getOrderBillById(orderBillId);
        if(orderBill.getOrderStatusList() != null && orderBill.getOrderStatusList().get(orderBill.getOrderStatusList().size() - 1).getStatus() != OrderStatus.CREATED) {
            throw new CustomException(ErrorConstant.ORDER_BILL_STATUS_INVALID);
        }
        Double canceledProductPrice = 0.0;
        List<ItemOrderEntity> itemOrderList = orderBill.getItemOrderList();
        orderBill.getOrderStatusList().add(OrderStatusEntity.builder()
                .status(OrderStatus.PROCESSING)
                .note(body.getNote())
                .orderBill(orderBill)
                .build());
        if(body.getCanceledProductList() != null && body.getCanceledProductList().size() > 0) {
            List<String> cancelledProductIdList = body.getCanceledProductList().stream().map(it -> it.getId()).collect(Collectors.toList());
            for (ItemOrderEntity item :itemOrderList) {
                if (cancelledProductIdList.contains(item.getProduct().getId())) {
                    item.setStatus(ItemOrderStatus.REFUSE);
                    item.setCancellationReason(
                            body.getCanceledProductList().stream().filter(canceledProduct -> item.getProduct().getId().equals(canceledProduct.getId()))
                                    .findFirst().orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND)).getCancellationReason()
                    );
                    canceledProductPrice += item.getPrice();
                }
            }
        }
        orderBill.setTotalBill(orderBill.getTotalProductPrice() - canceledProductPrice);
        orderBill.setTransaction(TransactionEntity.builder()
                        .orderBill(orderBill)
                        .paymentTotal(orderBill.getTotalBill())
                        .paid(0.0)
                        .status(TransactionStatus.UNPAID)
                .build());
        orderBillRepository.save(orderBill);
    }

    @Transactional
    public void refuseOrderBillById(String orderBillId, RefuseOrderBillByIdRequest body) {
        OrderBillEntity orderBill = getOrderBillById(orderBillId);
        OrderStatus lastStatus = orderBill.getOrderStatusList().get(orderBill.getOrderStatusList().size() - 1).getStatus();
        if(lastStatus == OrderStatus.CANCELLED || lastStatus == OrderStatus.SUCCEED) {
            throw new CustomException(ErrorConstant.ORDER_BILL_STATUS_INVALID);
        }
        orderBill.getOrderStatusList().add(OrderStatusEntity.builder()
                .orderBill(orderBill)
                .status(OrderStatus.CANCELLED)
                .note(body.getNote())
                .build()
        );
        orderBill.setTotalBill(0.0);

        List<ItemOrderEntity> itemOrderList = orderBill.getItemOrderList();
        for (ItemOrderEntity item :itemOrderList) {
            item.setStatus(ItemOrderStatus.REFUSE);
        }
        orderBillRepository.save(orderBill);
    }

    @Transactional
    public void succeedOrderBillById(String orderBillId) {
        OrderBillEntity orderBill = getOrderBillById(orderBillId);
        OrderStatus lastStatus = orderBill.getOrderStatusList().get(orderBill.getOrderStatusList().size() - 1).getStatus();
        if(lastStatus != OrderStatus.PROCESSING) {
            throw new CustomException(ErrorConstant.ORDER_BILL_STATUS_INVALID);
        }
        orderBill.getOrderStatusList().add(OrderStatusEntity.builder()
                .orderBill(orderBill)
                .status(OrderStatus.SUCCEED)
                .build()
        );
        orderBill.getTransaction().setStatus(TransactionStatus.COMPLETED);
        orderBill.getTransaction().setPaid(orderBill.getTotalBill());

        orderBillRepository.save(orderBill);
    }

    public List<GetAllOrderBillByUserIdAndStatus> getAllOrderBillByUserIdAndStatus(String userId, OrderStatus orderStatus) {
        return orderBillRepository.getAllOrderBillByUserIdAndStatus(userId, orderStatus.toString());
    }
}
