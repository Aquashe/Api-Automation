package com.stepDefinitions.ecommerce;

import com.api.exceptions.ApiException;
import com.api.pojo.ecommerce.model.OrderDetails;
import com.api.pojo.ecommerce.request.CreateOrderRequest;
import com.api.pojo.ecommerce.response.CreateOrderResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public class CreateOrder extends ApiBaseTest {

    RequestSpecification createOrderRequest;
    CreateOrderResponse createOrderResponse;

    @Given("Order payload with {string} and {string}")
    public void order_payload_with_and(String country, String productId) {
        OrderDetails orderDetails = OrderDetails.builder()
                .country(country)
                .productOrderedId(productId)
                .build();

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(orderDetails);

        createOrderRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .header("Authorization", RuntimeVariable.get("token", String.class))
                .body(CreateOrderRequest.builder()
                        .orders(orderDetailsList)
                        .build());
        GlobalData.requestSpecification = createOrderRequest;
    }
    @Then("Create Order response should be parsed correctly")
    public void create_order_response_should_be_parsed_correctly() {
        createOrderResponse = GlobalData.response.as(CreateOrderResponse.class);
        if(createOrderResponse == null)
            throw new ApiException("❌ Failed to parse CreateOrderResponse");
        RuntimeVariable.set("orderId", createOrderResponse.getOrders().get(0));
    }
    @Then("an orderId should be generated along with productId")
    public void an_order_id_should_be_generated_along_with_product_id() {
        if(createOrderResponse.getOrders().isEmpty())
            throw new ApiException("❌ OrderId should be generated");
        if(createOrderResponse.getProductOrderId().isEmpty())
            throw new ApiException("❌ Product Ids can't be null inside Orders response.");

        RuntimeVariable.set("orderId", createOrderResponse.getOrders().get(0));
        RuntimeVariable.set("ordersProductId", createOrderResponse.getProductOrderId().get(0));
    }
    @Then("message with {string} inside the Create Order response body.")
    public void message_with_inside_the_create_order_response_body(String message) {
        equals(createOrderResponse.getMessage(), message);
        RuntimeVariable.set("orderMessage", createOrderResponse.getMessage());
    }

}
