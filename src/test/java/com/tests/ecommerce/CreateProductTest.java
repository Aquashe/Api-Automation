package com.api.ecommerce.tests.api;

import com.api.ecommerce.pojo.response.AddProductResponse;

import com.api.ecommerce.tests.base.BaseTest;
import com.api.ecommerce.utils.GlobalData;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import java.io.File;


public class CreateProductTest extends BaseTest {

    @Test(priority = 2, dependsOnGroups = "login", groups = "product")
    public void createProduct(){
        RequestSpecification createProductRequest = requestMultiPart
                .header("Authorization", GlobalData.authToken)
                .param("productName","qwerty")
                .param("productAddedBy",GlobalData.userId)
                .param("productCategory","fashion")
                .param("productSubCategory","shirts")
                .param("productPrice","11500")
                .param("productDescription","Addias Originals")
                .multiPart("productImage", new File("src/test/resources/shirt.jpeg"));


        AddProductResponse addProductResponse = createProductRequest.when()
                .post("api/ecom/product/add-product")
                .then().assertThat().statusCode(201)
                .extract().response().as(AddProductResponse.class);

        GlobalData.productId = addProductResponse.getProductId();

        System.out.println("✅ Product added: " + GlobalData.productId);
        System.out.println("✅ Message: " + addProductResponse.getMessage());

    }
}
