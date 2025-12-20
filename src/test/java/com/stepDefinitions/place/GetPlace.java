package com.stepDefinitions.place;

import com.api.exceptions.ApiException;
import com.api.pojo.place.response.GetPlaceResponse;
import com.api.utils.GlobalData;
import com.base.ApiBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetPlace extends ApiBaseTest {

    RequestSpecification getPlaceRequest;
    GetPlaceResponse getPlaceResponse;

    @Given("Get Place payload with {string}")
    public void get_place_payload_with(String placeId) {
        getPlaceRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .pathParams("key", "qaclick123")
                .pathParams("placeId", placeId);
        GlobalData.requestSpecification = getPlaceRequest;
    }
    @Then("GetPlace response should be parsed correctly")
    public void get_place_response_should_be_parsed_correctly() {
        getPlaceResponse = GlobalData.response.as(GetPlaceResponse.class);
        if (getPlaceResponse == null)
            throw new ApiException("❌ Failed to parse GetPlaceResponse");
    }
    @Then("a place details should show inside responseBody")
    public void a_place_details_should_show_inside_response_body() {
        log.info("✅ location : {\n\tlatitude : {} \tlongitude : {}}", getPlaceResponse.getLocation().getLatitude(), getPlaceResponse.getLocation().getLongitude());
        log.info("✅ accuracy  : {}", getPlaceResponse.getAccuracy());
        log.info("✅ name : {}", getPlaceResponse.getName());
        log.info("✅ phone_number  : {}", getPlaceResponse.getPhone_number());
        log.info("✅ address : {}", getPlaceResponse.getAddress());
        log.info("✅ types : {}", getPlaceResponse.getTypes());
        log.info("✅ website  : {}", getPlaceResponse.getWebsite());
        log.info("✅ language : {}", getPlaceResponse.getLanguage());
    }

}
