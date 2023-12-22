package com.onnv.household.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.onnv.household.constants.SwaggerConstant.*;

@Data
public class UpdateDeliveryAddressByIdRequest {
    @Schema(example = PROVINCE_EX)
    @NotBlank
    private String province;

    @Schema(example = DISTRICT_EX)
    @NotBlank
    private String district;

    @Schema(example = WARD_EX)
    @NotBlank
    private String ward;

    @Schema(example = ADDRESS_DETAILS_EX)
    @NotBlank
    private String details;

    @Schema(example = PHONE_NUMBER_EX)
    @NotBlank
    @Pattern(regexp = PHONE_NUMBER_REGEX)
    private String phoneNumber;
}
