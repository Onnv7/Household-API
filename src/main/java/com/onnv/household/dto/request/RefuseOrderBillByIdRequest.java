package com.onnv.household.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.onnv.household.constants.SwaggerConstant.NOTE_EX;

@Data
public class RefuseOrderBillByIdRequest {

    @Schema(example = NOTE_EX)
    private String note;
}
