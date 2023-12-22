package com.onnv.household.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;


public interface GetProductNonHiddenResponse {
    String getId();
    String getName();
    Double getPrice();
    String getThumbnailUrl();
}
