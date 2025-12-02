package com.stepDefinitons;

import com.api.base.ApiBase;
import com.api.files.SpecBuilder;
import com.api.googleMaps.pojo.response.AddPlaceResponse;
import com.api.googleMaps.pojo.response.DeletePlaceResponse;
import com.api.googleMaps.pojo.response.GetPlaceResponse;
import com.api.utils.ResponseValidator;
import com.resources.ApiResources;
import com.resources.TestDataBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.junit.Assert.*;

public class StepDefinition extends ApiBase {

    RequestSpecification request;
    Response response;
    Class<?> responseClass;
    TestDataBuilder testDataBuilder = new TestDataBuilder();
    Object pojoResponse;
    static String placeId;

    @Given("Add Place Payload with {string} {string} {string} {string}")
    public void add_place_payload_with(String name, String phoneNumber, String Address, String language) {
        request = requestJsonPart
                .queryParam("key","qaclick123")
                .body(testDataBuilder.add_place_payload(name, phoneNumber, Address, language));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {
        response = setHttpMethodWithRequestSpecification(request,httpMethod, resource);
        pojoResponse = response.as(responseClass);
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_sucess_with_status_code(Integer statusCode) {
        response.then().spec(SpecBuilder.responseSpecification(statusCode))
                .extract().response();
    }

    @Then("the {string} in response body is {string}")
    public void the_in_response_body_is(String key, String value) {
        ResponseValidator.assertFieldValue(pojoResponse, key, value);
    }

    @Then("verify placeId created maps the {string} using {string}")
    public void verify_place_id_created_maps_the_using(String keyValue, String resource) {
        placeId = ((AddPlaceResponse)pojoResponse).getPlace_id();
        request = requestNoBodyPart
                .queryParam("key","qaclick123")
                .queryParam("place_id",placeId);
        response = setHttpMethodWithRequestSpecification(request, "GET", resource);
        pojoResponse = response.as(responseClass);
        assertEquals(keyValue, ((GetPlaceResponse)pojoResponse).getName());
        System.out.println("✅ Expected Name: " + keyValue +"\t\t Actual Name : "+ ((GetPlaceResponse)pojoResponse).getName());

    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() {
        request = requestJsonPart
                .queryParam("key","qaclick123")
                .body(testDataBuilder.deletePlace_payload(placeId));
    }

    public Response setHttpMethodWithRequestSpecification(RequestSpecification req, String httpMethod, String resource){
        ApiResources api = ApiResources.valueOf(resource);
        switch (httpMethod.toUpperCase()){
            case "POST"   :
                responseClass = AddPlaceResponse.class;
                return req.when().post(api.getResourse());
            case "GET"    :
                responseClass = GetPlaceResponse.class;
                return req.when().get(api.getResourse());
            case "PUT"    :
                responseClass = AddPlaceResponse.class;
                return req.when().put(api.getResourse());
            case "DELETE" :
                responseClass = DeletePlaceResponse.class;
                return req.when().delete(api.getResourse());
            default:
                throw new IllegalArgumentException("Unsupported HTTP Method: " + httpMethod);
        }
    }
}
