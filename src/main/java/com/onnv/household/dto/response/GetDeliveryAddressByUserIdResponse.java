package com.onnv.household.dto.response;

public interface GetDeliveryAddressByUserIdResponse {
    String getId();
    String getSupAddress();
    String getSubAddress();
    String getPhoneNumber();
    boolean getIsDefault();
}
