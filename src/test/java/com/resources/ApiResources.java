package com.resources;

public enum ApiResources {
    AddPlaceAPI("maps/api/place/add/json"),
    GetPlaceAPI("maps/api/place/get/json"),
    PutPlaceAPI("maps/api/place/update/json"),
    DeletePlaceAPI("maps/api/place/delete/json");

    public String resourse;

    ApiResources(String resourceName){
        this.resourse = resourceName;
    }

    public String getResourse(){
        return resourse;
    }
}
