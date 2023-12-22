package com.onnv.household.dto.request;

import com.onnv.household.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

import static com.onnv.household.constants.SwaggerConstant.*;

@Data
public class UpdateUserProfileByIdRequest {
    @Schema(example = FIRST_NAME_EX)
    @NotBlank
    private String firstName;

    @Schema(example = LAST_NAME_EX)
    @NotBlank
    private String lastName;

    @Schema(example = GENDER_EX)
    @NotNull
    private Gender gender;

    @Schema(example = BIRTH_DATE_EX)
    @NotNull
    private Date birthDate;
}
