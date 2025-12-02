package com.stepDefinitons;


import io.cucumber.java.Before;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario(){
        if(StepDefinition.placeId == null){
            StepDefinition stepDefinition = new StepDefinition();
            stepDefinition.add_place_payload_with("Middleline house", "(+91) 983 893 3937", "29, side layout, cohen 09", "Hindi-IN ") ;
            stepDefinition.user_calls_with_http_request("AddPlaceAPI", "POST");
            stepDefinition.verify_place_id_created_maps_the_using("Middleline house", "GetPlaceAPI");
        }
    }
}
