package com.api.googleMaps.pojo.request;

import com.api.googleMaps.pojo.model.LocationDetails;
import lombok.Data;

import java.util.List;

@Data
public class AddPlace {
    private LocationDetails location ;
    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private List<String> types;
    private String website;
    private String language;
}
