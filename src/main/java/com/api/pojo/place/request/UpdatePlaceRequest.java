package com.api.pojo.place.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePlaceRequest {
    private String place_id;
    private String address;
    private String key;
}
