package com.api.pojo.libary.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteBookRequest {
    @JsonProperty("ID")
    private String id;
}
