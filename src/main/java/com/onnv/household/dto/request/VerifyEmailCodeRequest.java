package com.onnv.household.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.onnv.household.constants.SwaggerConstant.*;

@Data
public class VerifyEmailCodeRequest {

    @Schema(example = EMAIL_EX)
    @NotBlank
    private String email;

    @Schema(example = CODE_EX)
    @NotBlank
    private String code;
}
