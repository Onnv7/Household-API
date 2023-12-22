package com.onnv.household.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.onnv.household.constants.SwaggerConstant.EMAIL_EX;
import static com.onnv.household.constants.SwaggerConstant.PASSWORD_EX;

@Data
public class ChangeNewPasswordRequest {
    @Schema(example = EMAIL_EX)
    @NotBlank
    private String email;

    @Schema(example = PASSWORD_EX)
    @NotBlank
    private String password;
}
