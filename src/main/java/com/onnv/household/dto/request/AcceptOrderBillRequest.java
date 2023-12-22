package com.onnv.household.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.onnv.household.constants.SwaggerConstant.*;

@Data
public class AcceptOrderBillRequest {
    private List<CanceledProduct> canceledProductList;

    @Schema(example = NOTE_EX)
    private String note;

    @Data
    public static class CanceledProduct {
        @Schema(example = ID_EX)
        @NotBlank
        private String id;

        @Schema(example = CANCELLATION_REASON_EX)
        private String cancellationReason;
    }
}
