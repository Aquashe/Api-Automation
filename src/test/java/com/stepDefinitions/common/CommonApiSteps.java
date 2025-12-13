package com.stepDefinitions.common;

import com.api.exceptions.ApiException;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import com.resources.core.ApiResource;
import com.resources.core.ApiResourceResolver;
import com.resources.library.BookApiResources;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonApiSteps extends ApiBaseTest {
    @When("user calls {string} with {string} http method")
    public void user_calls_with_http_method(String resourceName, String httpMethod) {
        Response response = setHttpMethodWithRequestSpecification(GlobalData.requestSpecification, httpMethod, ApiResourceResolver.resolve(resourceName))
                .then().extract().response();
        GlobalData.response = response;
        if(response.getBody().asString().isEmpty())
            throw new ApiException("❌ Empty response received from "+resourceName+" API");
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer statusCode) {
        GlobalData.response.then().spec(Spec_Builder.responseSpecification(statusCode));
    }
}
