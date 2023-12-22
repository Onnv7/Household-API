package com.onnv.household.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

import static com.onnv.household.constants.ConstraintConstant.MIN_QUANTITY_ITEM_ORDER;
import static com.onnv.household.constants.SwaggerConstant.*;

@Data
public class CreateOrderBillRequest {
    @NotNull
    private ShipmentDetails shipmentDetails;

    @NotNull
    @NotEmpty
    private List<ItemOrder> itemOrderList;

    @Schema(example = ORDER_BILL_NOTE_EX)
    @NotBlank
    private String note;

    @Data
    public static class ItemOrder {

        @Schema(example = ID_EX)
        @NotBlank
        private String productId;

        @Schema(example = TYPE_NAME_EX)
        private String typeName;

        @Schema(example = QUANTITY_EX)
        @Min(value = MIN_QUANTITY_ITEM_ORDER)
        private Integer quantity;

        @Schema(example = ITEM_NOTE_EX)
        private String note;
    }
    @Data
    static class ShipmentDetails {
        @Schema(example = FULL_ADDRESS_EX)
        @NotBlank
        private String address;

        @Schema(example = PHONE_NUMBER_EX)
        @NotBlank
        @Pattern(regexp = PHONE_NUMBER_REGEX)
        private String phoneNumber;

        @Schema(example = SHIPPING_NOTE_EX)
        @NotBlank
        private String note;
    }
}
