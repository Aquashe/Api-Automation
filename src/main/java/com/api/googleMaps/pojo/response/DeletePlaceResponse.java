package com.api.googleMaps.pojo.response;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class DeletePlaceResponse {
    private String status;
}
