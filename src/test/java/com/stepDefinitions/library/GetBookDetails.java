package com.stepDefinitions.library;

import com.api.exceptions.ApiException;
import com.api.pojo.libary.response.AddBookResponse;
import com.api.pojo.libary.response.BookDetailsResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import com.resources.ApiResources;
import com.resources.library.BookApiResources;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class GetBookDetails extends ApiBaseTest {
    RequestSpecification getBookRequest;
    Response response;
    List<BookDetailsResponse> bookDetailsResponse;

    @Given("Book with Id {string}")
    public void book_with_id(String bookId) {
        getBookRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .pathParams("bookId", bookId);
        GlobalData.requestSpecification = getBookRequest;
    }
    @Given("Book with author {string}")
    public void book_with(String authorName) {
        getBookRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .pathParams("authorName", authorName);
        GlobalData.requestSpecification = getBookRequest;
    }
    @Then("GetBook response should be parsed correctly")
    public void get_book_response_should_be_parsed_correctly() {
        bookDetailsResponse = Arrays.asList(GlobalData.response.as(BookDetailsResponse[].class));
        if(bookDetailsResponse.isEmpty())
            throw new ApiException("❌ Failed to parse BookDetailsResponse");
    }
    @Then("shows response with Book details")
    public void shows_response_with_book_details() {
        bookDetailsResponse.forEach( bookDetailsResponse -> {
            log.info("✅ book_name : {}", bookDetailsResponse.getBookName());
            log.info("✅ isbn : {}", bookDetailsResponse.getIsbn());
            log.info("✅ aisle : {}", bookDetailsResponse.getAisle());
            if(bookDetailsResponse.getAuthor() != null)
                log.info("✅ aisle : {}", bookDetailsResponse.getAuthor());
        });
    }
}
