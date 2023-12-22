package com.onnv.household.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterResponse {
    private String refreshToken;
    private String accessToken;
    private String userId;
}
