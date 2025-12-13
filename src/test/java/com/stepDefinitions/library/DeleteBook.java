package com.stepDefinitions.library;

import com.api.exceptions.ApiException;
import com.api.pojo.libary.request.DeleteBookRequest;
import com.api.pojo.libary.response.AddBookResponse;
import com.api.pojo.libary.response.DeleteBookResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import com.context.ContextHolder;
import com.context.ScenarioContext;
import com.resources.library.BookApiResources;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteBook extends ApiBaseTest {

    RequestSpecification deleteBookRequest;
    Response response;
    DeleteBookResponse deleteBookResponse;

    @Given("Delete Book payload")
    public void delete_book_payload_with() {
        deleteBookRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(DeleteBookRequest.builder().id(RuntimeVariable.get("bookId", String.class)).build());
        GlobalData.requestSpecification = deleteBookRequest;
    }
    @Then("DeleteBook response should be parsed correctly")
    public void delete_book_response_should_be_parsed_correctly() {
        deleteBookResponse = GlobalData.response.as(DeleteBookResponse.class);
        if (deleteBookResponse == null)
            throw new ApiException("❌ Failed to parse DeleteBookResponse");

    }
    @Then("Msg in the response body is {string}")
    public void msg_in_the_response_body_is(String msg) {
        equals(deleteBookResponse.getMsg(), msg);
        RuntimeVariable.set("deleteBookMsg", deleteBookResponse.getMsg());
    }
}
