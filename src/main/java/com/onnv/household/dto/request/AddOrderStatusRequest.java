package com.onnv.household.dto.request;

import com.onnv.household.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.onnv.household.constants.SwaggerConstant.*;

@Data
public class AddOrderStatusRequest {
    @Schema(example = ORDER_STATUS_EX)
    @NotNull
    private OrderStatus status;

    @Schema(example = NOTE_EX)
    @NotBlank
    private String note;
}
