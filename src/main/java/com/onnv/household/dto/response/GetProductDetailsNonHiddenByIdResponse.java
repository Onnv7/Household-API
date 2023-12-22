package com.onnv.household.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetProductDetailsNonHiddenByIdResponse {
    private String id;
    private String name;
    private String description;
    private String price;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ProductClassification> productClassificationList;

    @Data
    static class ProductClassification {
        private String name;
        private double price;
    }
}
