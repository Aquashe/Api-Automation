package com.resources.place;

import com.resources.core.ApiResource;

public enum PlaceApiResources implements ApiResource {

   AddPlaceAPI("maps/api/place/add/json?key={key}"),
   GetPlaceAPI("maps/api/place/get/json?place_id={placeId}&key={key}"),
   PutPlaceAPI("maps/api/place/update/json"),
   DeletePlaceAPI("maps/api/place/delete/json?key={key}");

    private String resource;

    PlaceApiResources(String resourceName){
        this.resource = resourceName;
    }

    @Override
    public String getResource() {
        return resource;
    }
}
