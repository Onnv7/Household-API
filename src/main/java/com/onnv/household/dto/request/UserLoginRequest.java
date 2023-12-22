package com.onnv.household.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

import static com.onnv.household.constants.ConstraintConstant.*;
import static com.onnv.household.constants.SwaggerConstant.*;

@Data
public class UserLoginRequest {
    @Schema(example = EMAIL_EX)
    @NotBlank
    @Email
    private String email;

    @Schema(example = PASSWORD_EX)
    @NotBlank
    @Size(min = MIN_PWD_LENGTH, max = MAX_PWD_LENGTH)
    private String password;
}
