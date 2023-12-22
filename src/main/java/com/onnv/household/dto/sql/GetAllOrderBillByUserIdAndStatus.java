package com.onnv.household.dto.sql;

import java.util.Date;

public interface GetAllOrderBillByUserIdAndStatus {
    String getId();
    Double getTotalProductPrice();
    Double getTotalBill();
    String getTransactionStatus();
    Date getCreatedAt();
}
