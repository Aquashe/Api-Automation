package com.tests.ecommerce;


import com.api.pojo.ecommerce.response.DeleteOrderResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class DeleteOrderTest extends ApiBaseTest {

    @Test(priority = 5, dependsOnGroups = "order")
    public void deleteOrder(){
        RequestSpecification deleteOrderRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .header("Authorization", GlobalData.authToken)
                .pathParam("orderId", RuntimeVariable.get("orderId"));

        Response response =deleteOrderRequest
                .when().delete("api/ecom/order/delete-order/{orderId}")
                .then()
                .spec(Spec_Builder.responseSpecification(200))
                .extract().response();

        DeleteOrderResponse deleteOrderResponse = response.as(DeleteOrderResponse.class);
        log.info("✅ Message: {}", deleteOrderResponse.getMessage());
    }
}
