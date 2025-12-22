package com.stepDefinitions.ecommerce;

import com.api.exceptions.ApiException;
import com.api.pojo.ecommerce.response.GetOrderResponse;
import com.api.utils.GlobalData;

import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

public class GetOrderDetails extends ApiBaseTest {
    RequestSpecification getOrderRequest;
    GetOrderResponse getOrderResponse;

    @Given("GetOrder payload with {string}")
    public void get_order_payload_with(String orderId) {
        getOrderRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .header("Authorization", RuntimeVariable.get("token", String.class))
                .queryParam("id", orderId);
        GlobalData.requestSpecification = getOrderRequest;
    }
    @Then("Get Order response should be parsed correctly")
    public void get_order_response_should_be_parsed_correctly() {
        getOrderResponse = GlobalData.response.as(GetOrderResponse.class);
        if(getOrderResponse == null)
            throw new ApiException("❌ Failed to parse LoginResponse");
    }
    @Then("message with {string} inside Get Order response body.")
    public void message_with_inside_get_order_response_body(String message) {
       equals(getOrderResponse.getMessage(), message);
        RuntimeVariable.set("getOrderMessage",getOrderResponse.getMessage());
        RuntimeVariable.set("orderData",getOrderResponse.getData());


    }

}
