package com.onnv.household.dto.request;

import com.onnv.household.enums.CategoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.onnv.household.constants.SwaggerConstant.*;

@Data
public class CreateCategoryRequest {

    @Schema(example = CATEGORY_NAME_EX)
    @NotBlank
    private String name;

    @Schema(example = CATEGORY_STATUS_EX)
    @NotNull
    private CategoryStatus status;

    @Schema(example = ID_EX)
    private String parentCategoryId;

//    @NotNull
//    private MultipartFile image;
}
