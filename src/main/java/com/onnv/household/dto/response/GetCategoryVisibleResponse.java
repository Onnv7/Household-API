package com.onnv.household.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class GetCategoryVisibleResponse {
    private String id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<GetCategoryVisibleResponse> childCategoryList;
}
