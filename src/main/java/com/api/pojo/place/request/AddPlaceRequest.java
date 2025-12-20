package com.api.pojo.place.request;

import com.api.pojo.place.model.LoacationDetails;
import lombok.Data;

import java.util.List;

@Data
public class AddPlaceRequest {
    private LoacationDetails location ;
    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private List<String> types;
    private String website;
    private String language;
}
