package com.onnv.household.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.onnv.household.constants.SwaggerConstant.REFRESH_TOKEN_EX;

@Data
public class RefreshTokenRequest {

    @Schema(example = REFRESH_TOKEN_EX)
    @NotBlank
    private String refreshToken;
}
