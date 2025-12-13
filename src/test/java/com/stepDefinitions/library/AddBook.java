package com.stepDefinitions.library;

import com.api.exceptions.ApiException;
import com.api.payloads.library.AddBookPayload;
import com.api.pojo.libary.response.AddBookResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import com.resources.library.BookApiResources;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddBook extends ApiBaseTest {

    private RequestSpecification createBookRequest;
    private Response response;
    private AddBookResponse addBookResponse;

    @Given("Add Book Payload with {string} {string} {string} {string}")
    public void add_book_payload_with(String name, String isbn, String aisle, String authorName) {
         createBookRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .header("Content-Type", "application/json")
                .body(AddBookPayload.addBookRequestData(name, isbn, aisle, authorName));
         GlobalData.requestSpecification = createBookRequest;
    }
    @Then("AddBook response should be parsed correctly")
    public void add_book_response_should_be_parsed_correctly() {
        addBookResponse = GlobalData.response.as(AddBookResponse.class);
        if (addBookResponse == null)
            throw new ApiException("❌ Failed to parse AddBookResponse");

    }
    @Then("the Msg in the in response body is {string}")
    public void the_in_the_in_response_body_is(String msg) {
        equals(addBookResponse.getMsg(), msg);
        RuntimeVariable.set("bookAddedMsg", addBookResponse.getMsg());
    }
    @Then("a bookId should be created inside in responseBody")
    public void a_book_id_should_be_created_inside_in_response_body() {
        String bookId =  addBookResponse.getId();
        if(bookId == null || bookId.isBlank())
            throw new ApiException("Id can't be null or empty");
        RuntimeVariable.set("bookId", bookId);
    }

}
