package com.api.pojo.place.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeletePlaceRequest {
    private String place_id;
}
