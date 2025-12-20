package com.api.pojo.place.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoacationDetails {
    private Double lat;
    private Double lng;
}
