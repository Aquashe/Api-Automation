package com.api.ecommerce.tests.api;

import com.api.ecommerce.pojo.response.GetOrderResponse;
import com.api.ecommerce.tests.base.BaseTest;
import com.api.ecommerce.utils.GlobalData;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GetOrderTest extends BaseTest {

    @Test(priority = 4, dependsOnGroups = "order")
    public void getOrderDetails(){
        RequestSpecification getOrderRequest = requestNoBodyPart
                .header("Authorization", GlobalData.authToken)
                .param("id", GlobalData.orderId);

        GetOrderResponse getOrderResponse = getOrderRequest.when()
                .get("api/ecom/order/get-orders-details")
                .then().assertThat().statusCode(200)
                .extract().response().as(GetOrderResponse.class);

        System.out.println("✅ _id: " + getOrderResponse.getData().get_id());
        System.out.println("✅ orderById: " + getOrderResponse.getData().getOrderById());
        System.out.println("✅ orderBy: " + getOrderResponse.getData().getOrderBy());
        System.out.println("✅ productOrderedId: " + getOrderResponse.getData().getProductOrderedId());
        System.out.println("✅ productName: " + getOrderResponse.getData().getProductName());
        System.out.println("✅ country: " + getOrderResponse.getData().getCountry());
        System.out.println("✅ productDescription: " + getOrderResponse.getData().getProductDescription());
        System.out.println("✅ productImage: " + getOrderResponse.getData().getProductImage());
        System.out.println("✅ orderPrice: " + getOrderResponse.getData().getOrderPrice());
        System.out.println("✅ __v: " + getOrderResponse.getData().get__v());
        System.out.println("✅ message: " + getOrderResponse.getMessage());

    }
}
