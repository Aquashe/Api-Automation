package com.api.pojo.place.response;


import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized

public class AddPlaceResponse {
    private  String status;
    private  String place_id;
    private  String scope;
    private  String reference;
    private  String id;
}
