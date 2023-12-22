package com.onnv.household.dto.request;

import com.onnv.household.enums.ProductStatus;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.onnv.household.constants.ConstraintConstant.MIN_PRODUCT_PRICE;
import static com.onnv.household.constants.SwaggerConstant.*;

@Data
@Schema()
public class CreateProductRequest {

    @Schema(example = PRODUCT_NAME_EX)
    @NotBlank
    private String name;

    @Schema()
    @NotNull
    private MultipartFile thumbnailImage;

    @Schema()
    @NotNull
    private List<MultipartFile> imageList;

    @Schema(example = PRODUCT_PRICE_EX)
//    @Min(MIN_PRODUCT_PRICE)
    @DecimalMin(value = "999", inclusive = false, message = "Must be greater than 1000")
    private Double price;

    @Schema(example = PRODUCT_DESCRIPTION_EX)
    @NotBlank
    private String description;

    @Schema(example = PRODUCT_STATUS_EX)
    @NotNull
    private ProductStatus status;

    @Schema(example = ID_EX)
    @NotBlank
    @ApiModelProperty(notes = "categoryId ji")
    private String categoryId;


    @Valid
    private List<ProductClassification> productClassificationList;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductClassification {
        @Schema(example = TYPE_NAME_EX)
        @NotBlank
        private String name;

        @Schema(example = PRODUCT_CLASSIFICATION_PRICE_EX)
        @Min(value = MIN_PRODUCT_PRICE)
        private Double price;
    }
}
