package com.tests.ecommerce;


import com.api.pojo.ecommerce.response.DeleteProductResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class DeleteProductTest extends ApiBaseTest {

    @Test(priority = 6, dependsOnGroups = "product")
    public void deleteProduct(){
        RequestSpecification deleteProductRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .header("Authorization", GlobalData.authToken)
                .pathParam("productId", RuntimeVariable.get("productId"));

        Response response =deleteProductRequest
                .when().delete("api/ecom/product/delete-product/{productId}")
                .then()
                .spec(Spec_Builder.responseSpecification(200))
                .extract().response();

        DeleteProductResponse deleteProductResponse = response.as(DeleteProductResponse.class);

        log.info("✅ Message: {}", deleteProductResponse.getMessage());
    }
}
