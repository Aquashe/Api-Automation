package com.tests.ecommerce;

import com.api.pojo.ecommerce.response.GetOrderResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class GetOrderTest extends ApiBaseTest {

    @Test(priority = 4, dependsOnGroups = "order")
    public void getOrderDetails(){
        RequestSpecification getOrderRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .header("Authorization", GlobalData.authToken)
                .param("id", RuntimeVariable.get("orderId").toString());

        Response response = getOrderRequest.when()
                .get("api/ecom/order/get-orders-details")
                .then()
                .spec(Spec_Builder.responseSpecification(200))
                .extract().response();

        GetOrderResponse getOrderResponse = response.as(GetOrderResponse.class);

        log.info("✅ _id: {}", getOrderResponse.getData().get_id());
        log.info("✅ orderById: {}", getOrderResponse.getData().getOrderById());
        log.info("✅ orderBy: {}", getOrderResponse.getData().getOrderBy());
        log.info("✅ productOrderedId: {}", getOrderResponse.getData().getProductOrderedId());
        log.info("✅ productName: {}", getOrderResponse.getData().getProductName());
        log.info("✅ country: {}", getOrderResponse.getData().getCountry());
        log.info("✅ productDescription: {}", getOrderResponse.getData().getProductDescription());
        log.info("✅ productImage: {}", getOrderResponse.getData().getProductImage());
        log.info("✅ orderPrice: {}", getOrderResponse.getData().getOrderPrice());
        log.info("✅ __v: {}", getOrderResponse.getData().get__v());
        log.info("✅ message: {}", getOrderResponse.getMessage());

    }
}
