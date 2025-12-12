package com.api.ecommerce.tests.api;

import com.api.ecommerce.pojo.model.OrderDetails;
import com.api.ecommerce.pojo.request.CreateOrderRequest;
import com.api.ecommerce.pojo.response.CreateOrderResponse;
import com.api.ecommerce.tests.base.BaseTest;
import com.api.ecommerce.utils.GlobalData;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderTest extends BaseTest {

    @Test(priority = 3, dependsOnGroups = "product", groups = "order")
    public void createOrder(){

        OrderDetails orderDetails = OrderDetails.builder()
                .country("British Indian Ocean Territory")
                .productOrderedId(GlobalData.productId)
                .build();

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(orderDetails);

        RequestSpecification createOrderRequest = requestJsonPart
                .header("Authorization", GlobalData.authToken)
                .body(CreateOrderRequest.builder()
                        .orders(orderDetailsList)
                        .build());

        CreateOrderResponse createOrderResponse = createOrderRequest.when()
                .post("api/ecom/order/create-order")
                .then().assertThat().statusCode(201)
                .extract().response().as(CreateOrderResponse.class);

        GlobalData.orderId = createOrderResponse.getOrders().getFirst();
        System.out.println("✅ Order added: " + GlobalData.orderId);
        System.out.println("✅ Product: " + createOrderResponse.getProductOrderId().getFirst());
        System.out.println("✅ Message: " + createOrderResponse.getMessage());
    }
}
