package com.onnv.household.controller;


import com.onnv.household.constants.SuccessConstant;
import com.onnv.household.dto.request.*;
import com.onnv.household.dto.response.GetOrderBillDetailsByIdResponse;
import com.onnv.household.dto.sql.GetAllOrderBillByStatus;
import com.onnv.household.dto.sql.GetAllOrderBillByUserIdAndStatus;
import com.onnv.household.enums.OrderStatus;
import com.onnv.household.model.ResponseAPI;
import com.onnv.household.service.OrderBillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onnv.household.constants.EndpointConstant.*;
import static com.onnv.household.constants.SwaggerConstant.*;

@Tag(name = ORDER_BILL_TAG)
@RestController
@RequestMapping(ORDER_BILL_PATH)
@RequiredArgsConstructor
public class OrderBillController {
    private final OrderBillService orderBillService;

    @Operation(summary = ORDER_CREATE_SUM)
    @PostMapping(path = POST_ORDER_BILL_CREATE_SUB_PATH)
    public ResponseEntity<ResponseAPI> createNewDeliveryAddress(@RequestBody @Valid CreateOrderBillRequest body, @PathVariable(USER_ID) String userId) {
        orderBillService.createOrderBill(body, userId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.CREATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = ORDER_ADD_STATUS_SUM)
    @PostMapping(path = POST_ORDER_BILL_ADD_STATUS_SUB_PATH)
    public ResponseEntity<ResponseAPI> addOrderStatusForBill(@RequestBody @Valid AddOrderStatusRequest body, @PathVariable(ORDER_ID) String orderId) {
        orderBillService.addOrderStatusForBill(orderId, body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.CREATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = ORDER_GET_DETAILS_BY_ID_SUM)
    @GetMapping(path = GET_ORDER_BILL_DETAILS_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> getOrderBillDetailsById(@PathVariable(ORDER_ID) String orderId) {
        GetOrderBillDetailsByIdResponse resData = orderBillService.getOrderBillDetailsById(orderId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.GET_SUCCESS)
                .data(resData)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = ORDER_GET_ALL_BY_STATUS_SUM)
    @GetMapping(path = GET_ORDER_BILL_ALL_BY_STATUS_SUB_PATH)
    public ResponseEntity<ResponseAPI> getAllOrderBillByStatus(
            @Parameter(name = ORDER_STATUS_KEY, required = true, example = "CREATED")
            @RequestParam(name = ORDER_STATUS_KEY) OrderStatus status
            ) {
        List<GetAllOrderBillByStatus> resData = orderBillService.getAllOrderBillByStatus(status);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.GET_SUCCESS)
                .data(resData)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = ORDER_PATCH_ACCEPT_BY_ID_SUM)
    @PatchMapping(path = PATCH_ORDER_BILL_ACCEPT_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> acceptOrderBillById(@PathVariable(ORDER_ID) String orderId, @RequestBody @Valid AcceptOrderBillRequest body) {
        orderBillService.acceptOrderBillById(orderId, body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.UPDATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = ORDER_PATCH_REFUSE_BY_ID_SUM)
    @PatchMapping(path = PATCH_ORDER_BILL_REFUSE_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> refuseOrderBillById(@PathVariable(ORDER_ID) String orderId, @RequestBody @Valid RefuseOrderBillByIdRequest body) {
        orderBillService.refuseOrderBillById(orderId, body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.UPDATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @Operation(summary = ORDER_PATCH_SUCCEED_BY_ID_SUM)
    @PatchMapping(path = PATCH_ORDER_BILL_SUCCEED_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> succeedOrderBillById(@PathVariable(ORDER_ID) String orderId) {
        orderBillService.succeedOrderBillById(orderId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.UPDATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = ORDER_GET_ALL_BY_USER_ID_AND_STATUS_SUM)
    @GetMapping(path = GET_ORDER_BILL_ALL_BY_USER_ID_AND_STATUS_SUB_PATH)
    public ResponseEntity<ResponseAPI> succeedOrderBillById(@PathVariable(USER_ID) String userId, @RequestParam(ORDER_STATUS_KEY) OrderStatus status) {
        List<GetAllOrderBillByUserIdAndStatus> resData = orderBillService.getAllOrderBillByUserIdAndStatus(userId, status);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.GET_SUCCESS)
                .data(resData)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
