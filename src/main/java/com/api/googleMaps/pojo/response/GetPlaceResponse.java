package com.api.googleMaps.pojo.response;

import com.api.googleMaps.pojo.model.Location;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class GetPlaceResponse {
    private Location location;
    private String accuracy;
    private String name;
    private String phone_number;
    private String address;
    private String types;
    private String website;
    private String language;
}
