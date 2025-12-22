package com.stepDefinitions.ecommerce;

import com.api.exceptions.ApiException;
import com.api.pojo.ecommerce.response.AddProductResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;

import java.io.File;

public class CreateProduct extends ApiBaseTest {

    RequestSpecification createProductRequest;
    AddProductResponse addProductResponse;

    @Given("Product payload with {string} {string}  {string} {string}  {string} {string}  {string}")
    public void product_payload_with(String productName, String productCategory, String productSubCategory, String productPrice, String productDescription,
                                     String productFor, String productImage) {
        createProductRequest = given()
                .spec(Spec_Builder.multiPartRequestSpecification("baseUrl"))
                .header("Authorization", RuntimeVariable.get("token", String.class))
                .param("productName",productName)
                .param("productAddedBy",RuntimeVariable.get("userId", String.class))
                .param("productCategory",productCategory)
                .param("productSubCategory",productSubCategory)
                .param("productPrice",productPrice)
                .param("productDescription",productDescription)
                .param("productFor", productFor)
                .multiPart("productImage", new File("src/test/resources/"+productImage));
        GlobalData.requestSpecification = createProductRequest;
    }

    @Then("Create Product response should be parsed correctly")
    public void create_product_response_should_be_parsed_correctly() {
        addProductResponse = GlobalData.response.as(AddProductResponse.class);
        if(addProductResponse == null)
            throw new ApiException("❌ Failed to parse AddProductResponse");
    }
    @Then("a product  should be generated with productId")
    public void a_product_should_be_generated_with_product_id() {
        if(addProductResponse.getProductId().isEmpty())
            throw new ApiException("❌ Failed to generate productId");
        RuntimeVariable.set("productId", addProductResponse.getProductId());

    }
    @Then("message with {string} inside Create product response body.")
    public void message_with_inside_create_product_response_body(String message) {
        equals(addProductResponse.getMessage(), message);
        RuntimeVariable.set("productCreateMsg", addProductResponse.getMessage());
    }

}
