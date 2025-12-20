package com.api.payloads.place;

import com.api.pojo.place.model.LoacationDetails;
import com.api.pojo.place.request.AddPlaceRequest;

import java.util.List;

public class AddPlacePayload {
    /**
     * @param latitude    Double
     * @param longitude   Double
     * @param accuracy    Integer
     * @param houseName   String
     * @param phoneNumber String
     * @param address     String
     * @param website     String
     * @param types       List<String>
     * @param language    String
     * @return Object(AddPlaceRequest)
     */
    public static AddPlaceRequest settingAddPlaceBody(
            double latitude,
            double longitude,
            int accuracy,
            String houseName,
            String phoneNumber,
            String address,
            String website,
            List<String> types,
            String language) {

        AddPlaceRequest addPlaceBody = new AddPlaceRequest();
        addPlaceBody.setLocation(LoacationDetails.builder().lat(latitude).lng(longitude).build());
        addPlaceBody.setAccuracy(accuracy);
        addPlaceBody.setName(houseName);
        addPlaceBody.setPhone_number(phoneNumber);
        addPlaceBody.setAddress(address);
        addPlaceBody.setWebsite(website);
        addPlaceBody.setTypes(types);
        addPlaceBody.setLanguage(language);
        return addPlaceBody;
    }
}
