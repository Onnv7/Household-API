package com.onnv.household.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenResponse {
    private String refreshToken;
    private String accessToken;
}
