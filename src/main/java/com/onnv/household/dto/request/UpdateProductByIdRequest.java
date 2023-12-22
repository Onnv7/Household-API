package com.onnv.household.dto.request;

import com.onnv.household.enums.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

import static com.onnv.household.constants.ConstraintConstant.MIN_PRODUCT_PRICE;
import static com.onnv.household.constants.SwaggerConstant.*;

@Data
public class UpdateProductByIdRequest {
    @Schema(example = PRODUCT_NAME_EX)
    @NotBlank
    private String name;

//    @Schema()
//    private MultipartFile thumbnailImage;
//
//    @Schema()
//    private List<MultipartFile> imageList;

    @Schema(example = PRODUCT_PRICE_EX)
    @Min(MIN_PRODUCT_PRICE)
    private double price;

    @Schema(example = PRODUCT_DESCRIPTION_EX)
    @NotBlank
    private String description;

    @Schema(example = PRODUCT_STATUS_EX)
    @NotNull
    private ProductStatus status;

    @Schema(example = ID_EX)
    @NotBlank
    private String categoryId;


    @Valid
    private List<ProductClassification> productClassificationList;

    @Data
    public static class ProductClassification {
        @Schema(example = ID_EX)
        @NotBlank
        private String name;

        @Schema(example = PRODUCT_CLASSIFICATION_PRICE_EX)
        @Min(value = MIN_PRODUCT_PRICE)
        private double price;
    }
}
