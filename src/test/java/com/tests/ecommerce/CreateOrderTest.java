package com.tests.ecommerce;

import com.api.pojo.ecommerce.model.OrderDetails;
import com.api.pojo.ecommerce.request.CreateOrderRequest;
import com.api.pojo.ecommerce.response.CreateOrderResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CreateOrderTest extends ApiBaseTest {

    @Test(priority = 3, dependsOnGroups = "product", groups = "order")
    public void createOrder(){

        OrderDetails orderDetails = OrderDetails.builder()
                .country("British Indian Ocean Territory")
                .productOrderedId(RuntimeVariable.get("productId").toString())
                .build();

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(orderDetails);

        RequestSpecification createOrderRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .header("Authorization", GlobalData.authToken)
                .body(CreateOrderRequest.builder()
                        .orders(orderDetailsList)
                        .build());

        Response response = createOrderRequest.when()
                .post("api/ecom/order/create-order")
                .then().assertThat().statusCode(201)
                .extract().response();

        CreateOrderResponse createOrderResponse = response.as(CreateOrderResponse.class);
        RuntimeVariable.set("orderId", createOrderResponse.getOrders().get(0));

        log.info("✅ Order added: {}", RuntimeVariable.get("orderId"));
        log.info("✅ Product: {}", createOrderResponse.getProductOrderId().get(0));
        log.info("✅ Message: {}", createOrderResponse.getMessage());
    }
}
