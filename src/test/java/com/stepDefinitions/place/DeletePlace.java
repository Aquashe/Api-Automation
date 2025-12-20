package com.stepDefinitions.place;

import com.api.exceptions.ApiException;
import com.api.pojo.place.request.DeletePlaceRequest;
import com.api.pojo.place.response.DeletePlaceResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeletePlace extends ApiBaseTest {
    RequestSpecification deletePlaceRequest;
    DeletePlaceResponse deletePlaceResponse;

    @Given("Delete Place Payload")
    public void delete_place_payload() {
        deletePlaceRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .pathParams("key", "qaclick123")
                .body(DeletePlaceRequest.builder().place_id(RuntimeVariable.get("placeId", String.class)).build());
        GlobalData.requestSpecification = deletePlaceRequest;
    }
    @Then("DeletePlace response should be parsed correctly")
    public void delete_place_response_should_be_parsed_correctly() {
        deletePlaceResponse = GlobalData.response.as(DeletePlaceResponse.class);
        if (deletePlaceResponse == null)
            throw new ApiException("❌ Failed to parse DeletePlaceResponse");
    }
    @Then("after deleted the place status in the response body is {string}")
    public void after_deleted_the_place_status_in_the_response_body_is(String status) {
        equals(deletePlaceResponse.getStatus(), status);
        log.info("✅ status  : {}",deletePlaceResponse.getStatus());
    }
}
