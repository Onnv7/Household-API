package com.onnv.household.dto.response;

import com.onnv.household.enums.OrderStatus;
import com.onnv.household.enums.TransactionStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GetOrderBillDetailsByIdResponse {
    private String id;
    private ShipmentDetails shipmentDetails;
    private List<ItemOrder> itemOrderList;
    private Double totalProductPrice;
    private Double totalBill;
    private List<OrderBillStatus> orderStatusList;
    private Date createdAt;
    private Transaction transaction;
    @Data
    static class ShipmentDetails {
        private String address;
        private String phoneNumber;
        private String note;
    }
    @Data
    public static class ItemOrder {
        private String productId;
        private String productName;
        private String imageUrl;
        private String typeName;
        private Double quantity;
        private Double price;
        private String cancellationReason;
        private String note;
    }
    @Data
    static class OrderBillStatus {
        private OrderStatus status;
        private Date createdAt;
        private String note;
    }
    @Data
    static class Transaction {
        private String id;
        private Double paid;
        private TransactionStatus status;
    }
}
