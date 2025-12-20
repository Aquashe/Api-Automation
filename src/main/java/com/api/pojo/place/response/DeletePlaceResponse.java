package com.api.pojo.place.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class DeletePlaceResponse {
    private String status;
}
