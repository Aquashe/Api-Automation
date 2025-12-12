package com.tests.ecommerce;


import com.api.pojo.ecommerce.response.AddProductResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import java.io.File;

@Slf4j
public class CreateProductTest extends ApiBaseTest {

    @Test(priority = 2, dependsOnGroups = "login", groups = "product")
    public void createProduct(){
        RequestSpecification createProductRequest = given()
                .spec(Spec_Builder.multiPartRequestSpecification("baseUrl"))
                .header("Authorization", GlobalData.authToken)
                .param("productName","qwerty")
                .param("productAddedBy",GlobalData.userId)
                .param("productCategory","fashion")
                .param("productSubCategory","shirts")
                .param("productPrice","11500")
                .param("productDescription","Addias Originals")
                .multiPart("productImage", new File("src/test/resources/shirt.jpeg"));


        Response response = createProductRequest.when()
                .post("api/ecom/product/add-product")
                .then()
                .spec(Spec_Builder.responseSpecification(201))
                .extract().response();

        AddProductResponse addProductResponse = response.as(AddProductResponse.class);
        RuntimeVariable.set("productId", addProductResponse.getProductId());

        log.info("✅ Product added: {}", RuntimeVariable.get("productId"));
        log.info("✅ Message: {}", addProductResponse.getMessage());

    }
}
