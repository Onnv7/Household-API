package com.onnv.household.dto.sql;

import lombok.Data;

import java.util.Date;

public interface GetAllOrderBillByStatus {
    String getId();
    String getCustomerId();
    String getCustomerName();
    String getEmail();
    Double getTotalProductPrice();
    String getLastOrderStatus();
    Date getCreatedAt();
}
