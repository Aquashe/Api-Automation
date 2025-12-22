package com.stepDefinitions.ecommerce;

import com.api.exceptions.ApiException;
import com.api.pojo.ecommerce.response.DeleteOrderResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;

public class DeleteOrder extends ApiBaseTest {

    RequestSpecification deleteOrderRequest;
    DeleteOrderResponse deleteOrderResponse;

    @Given("Delete order payload")
    public void delete_order_payload() {
        deleteOrderRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .header("Authorization", RuntimeVariable.get("token", String.class))
                .pathParam("orderId", RuntimeVariable.get("orderId", String.class));
        GlobalData.requestSpecification =deleteOrderRequest;
    }
    @Then("Delete Order response should be parsed correctly")
    public void delete_order_response_should_be_parsed_correctly() {
        deleteOrderResponse = GlobalData.response.as(DeleteOrderResponse.class);
        if(deleteOrderResponse == null)
            throw new ApiException("❌ Failed to parse DeleteOrderResponse");
    }
    @Then("message with {string} inside the Delete Order response body.")
    public void message_with_inside_the_delete_order_response_body(String message) {
        equals(deleteOrderResponse.getMessage(), message);
        RuntimeVariable.set("deleteOrderMessage", deleteOrderResponse.getMessage());
    }


}
