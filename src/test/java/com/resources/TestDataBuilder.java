package com.resources;

import com.api.googleMaps.pojo.model.LocationDetails;
import com.api.googleMaps.pojo.request.AddPlace;
import com.api.googleMaps.pojo.request.DeletePlace;
import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {
    public AddPlace add_place_payload(String name, String phoneNumber, String Address, String language) {
        LocationDetails locationDetails = new LocationDetails();
        locationDetails.setLat(-38.383494);
        locationDetails.setLng(33.427362);

        AddPlace addPlaceBody = new AddPlace();
        addPlaceBody.setLocation(locationDetails);
        addPlaceBody.setAccuracy(50);
        addPlaceBody.setName(name);
        addPlaceBody.setPhone_number(phoneNumber);
        addPlaceBody.setAddress(Address);
        addPlaceBody.setWebsite("http://google.com");
        addPlaceBody.setTypes(new ArrayList<>(List.of("shoe park", "shop")));
        addPlaceBody.setLanguage(language);
        return addPlaceBody;
    }

    public DeletePlace deletePlace_payload(String placeId){
        DeletePlace deletePlace = new DeletePlace();
        deletePlace.setPlace_id(placeId);
        return deletePlace;
    }

}
