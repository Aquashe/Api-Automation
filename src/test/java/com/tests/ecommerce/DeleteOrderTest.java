package com.api.ecommerce.tests.api;

import com.api.ecommerce.pojo.response.DeleteOrderResponse;
import com.api.ecommerce.tests.base.BaseTest;
import com.api.ecommerce.utils.GlobalData;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class DeleteOrderTest extends BaseTest {

    @Test(priority = 5, dependsOnGroups = "order")
    public void deleteOrder(){
        RequestSpecification deleteOrderRequest = requestNoBodyPart
                .header("Authorization", GlobalData.authToken)
                .pathParam("orderId",GlobalData.orderId);

        DeleteOrderResponse deleteOrderResponse =deleteOrderRequest
                .when().delete("api/ecom/order/delete-order/{orderId}")
                .then().assertThat().statusCode(200)
                .extract().response().as(DeleteOrderResponse.class);

        System.out.println("✅ Message: " + deleteOrderResponse.getMessage());
    }
}
