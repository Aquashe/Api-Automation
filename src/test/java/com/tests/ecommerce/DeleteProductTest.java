package com.api.ecommerce.tests.api;


import com.api.ecommerce.pojo.response.DeleteProductResponse;
import com.api.ecommerce.tests.base.BaseTest;
import com.api.ecommerce.utils.GlobalData;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class DeleteProductTest extends BaseTest {

    @Test(priority = 6, dependsOnGroups = "product")
    public void deleteProduct(){
        RequestSpecification deleteProductRequest = requestNoBodyPart
                .header("Authorization", GlobalData.authToken)
                .pathParam("productId", GlobalData.productId);

        DeleteProductResponse deleteProductResponse =deleteProductRequest
                .when().delete("api/ecom/product/delete-product/{productId}")
                .then().assertThat().statusCode(200)
                .extract().response().as(DeleteProductResponse.class);

        System.out.println("✅ Message: " + deleteProductResponse.getMessage());
    }
}
