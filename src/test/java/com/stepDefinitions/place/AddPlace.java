package com.stepDefinitions.place;

import com.api.exceptions.ApiException;
import com.api.payloads.place.AddPlacePayload;
import com.api.pojo.place.response.AddPlaceResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class AddPlace extends ApiBaseTest {

    RequestSpecification addPlaceRequest;
    AddPlaceResponse addPlaceResponse;

    @Given("Add Place payload with {string} {string} {string} {string} {string} {string} {string} {string}  {string}")
    public void add_place_payload_with(String lat, String lng, String accuracy, String houseName, String phoneNumber, String address, String website,
                                       String types, String language) {

        double latitude = Double.parseDouble(lat);
        double longitude = Double.parseDouble(lng);
        int accuracyValue = Integer.parseInt(accuracy);

        List<String> typesList = Arrays.stream(types.split(","))
                .map(String :: trim)
                .toList();

         addPlaceRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .pathParams("key", "qaclick123")
                .body(AddPlacePayload.settingAddPlaceBody(latitude, longitude, accuracyValue, houseName, phoneNumber, address, website, typesList,language));
        GlobalData.requestSpecification = addPlaceRequest;
    }

    @Then("AddPlace response should be parsed correctly")
    public void add_place_response_should_be_parsed_correctly() {
        addPlaceResponse = GlobalData.response.as(AddPlaceResponse.class);
        if (addPlaceResponse == null)
            throw new ApiException("❌ Failed to parse AddPlaceResponse");
    }
    @Then("after added the place status in the response body is {string}")
    public void after_added_the_place_status_in_the_response_body_is(String status) {
        equals(addPlaceResponse.getStatus(), status);
        log.info("✅ status : {}", addPlaceResponse.getStatus());
    }
    @Then("a place_id should be generated inside responseBody")
    public void a_place_id_should_be_generated_inside_response_body() {
        if(addPlaceResponse.getPlace_id().isEmpty())
            throw new ApiException("❌ placeId can't be null or empty");
        RuntimeVariable.set("placeId", addPlaceResponse.getPlace_id());
        RuntimeVariable.set("scope", addPlaceResponse.getScope());
        RuntimeVariable.set("reference", addPlaceResponse.getReference());
        RuntimeVariable.set("id", addPlaceResponse.getId());
    }


}
