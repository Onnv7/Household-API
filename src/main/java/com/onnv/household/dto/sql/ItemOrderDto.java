package com.onnv.household.dto.sql;

public interface ItemOrderDto {
     String getProductId();
     String getProductName();
     String getImageUrl();
     String getTypeName();
     Double getQuantity();
     Double getPrice();
     String getCancellationReason();
     String getNote();
}
