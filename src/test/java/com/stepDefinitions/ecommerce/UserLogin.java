package com.stepDefinitions.ecommerce;

import com.api.exceptions.ApiException;
import com.api.pojo.ecommerce.request.LoginRequest;
import com.api.pojo.ecommerce.response.LoginResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;

public class UserLogin extends ApiBaseTest {

    RequestSpecification loginRequest;
    LoginResponse loginResponse;

    @Given("Login payload with {string} and {string}")
    public void login_payload_with_and(String username, String password) {
        loginRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(LoginRequest.builder()
                        .userEmail(username)
                        .userPassword(password)
                        .build());
        GlobalData.requestSpecification = loginRequest;
    }

    @Then("Login response should be parsed correctly")
    public void login_response_should_be_parsed_correctly() {
        loginResponse = GlobalData.response.as(LoginResponse.class);
        if (loginResponse == null)
            throw new ApiException("❌ Failed to parse LoginResponse");
    }

    @Then("token should be generated with userId")
    public void token_should_be_generated_with_user_id() {
        if (loginResponse.getToken().isEmpty())
            throw new ApiException("❌ Token didn't get generated for the user");
        RuntimeVariable.set("token", loginResponse.getToken());
    }

    @Then("message with {string} inside response body.")
    public void message_with_inside_response_body(String message) {
        equals(loginResponse.getMessage(), message);
        RuntimeVariable.set("userId", loginResponse.getUserId());
        RuntimeVariable.set("message", loginResponse.getMessage());
    }

}
