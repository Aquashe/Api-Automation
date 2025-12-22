package com.stepDefinitions.ecommerce;

import com.api.exceptions.ApiException;
import com.api.pojo.ecommerce.response.DeleteProductResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;

public class DeleteProduct extends ApiBaseTest {

    RequestSpecification deleteProductRequest;
    DeleteProductResponse deleteProductResponse;

    @Given("Delete Product")
    public void delete_product() {
        deleteProductRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .header("Authorization", RuntimeVariable.get("token", String.class))
                .pathParam("productId", RuntimeVariable.get("productId", String.class));
        GlobalData.requestSpecification = deleteProductRequest;
    }
    @Then("Delete Product response should be parsed correctly")
    public void delete_product_response_should_be_parsed_correctly() {
        deleteProductResponse = GlobalData.response.as(DeleteProductResponse.class);
        if(deleteProductResponse == null)
            throw new ApiException("❌ Failed to parse DeleteProductResponse");
    }
    @Then("message with {string} inside Delete product response body.")
    public void message_with_inside_delete_product_response_body(String message) {
        equals(deleteProductResponse.getMessage(), message);
        RuntimeVariable.set("productDeleteMsg", deleteProductResponse.getMessage());
    }
}
