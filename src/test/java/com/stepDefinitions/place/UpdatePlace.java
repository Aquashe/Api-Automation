package com.stepDefinitions.place;

import com.api.exceptions.ApiException;
import com.api.pojo.place.request.UpdatePlaceRequest;
import com.api.pojo.place.response.UpdatePlaceResponse;
import com.api.utils.GlobalData;
import com.base.ApiBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdatePlace extends ApiBaseTest {
    RequestSpecification updatePlaceRequest;
    UpdatePlaceResponse updatePlaceResponse;

    @Given("Update Place payload with {string} {string} {string}")
    public void update_place_payload_with(String placeId, String address, String key) {
        updatePlaceRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(UpdatePlaceRequest.builder().place_id(placeId).address(address).key(key).build());
        GlobalData.requestSpecification = updatePlaceRequest;
    }
    @Then("UpdatePlace response should be parsed correctly")
    public void update_place_response_should_be_parsed_correctly() {
        updatePlaceResponse = GlobalData.response.as(UpdatePlaceResponse.class);
        if(updatePlaceResponse == null)
            throw new ApiException("❌ Failed to parse UpdatePlaceResponse");
    }
    @Then("a msg with {string} should show inside responseBody")
    public void a_msg_with_should_show_inside_response_body(String msg) {
        equals(updatePlaceResponse.getMsg(), msg);
        log.info("✅ msg  : {}", updatePlaceResponse.getMsg());
    }
}
