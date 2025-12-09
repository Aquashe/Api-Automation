package com.tests.library;

import com.api.files.SpecBuilder;
import com.api.pojo.libary.response.BookDetailsResponse;
import com.api.utils.GlobalData;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class FetchBookDetails extends ApiBaseTest {

    @DataProvider
    public Object[][] getbookIds(){
        Object[][] data = new Object[GlobalData.bookIds.size()][1];
        for(int i=0; i<GlobalData.bookIds.size(); i++)
            data[i][0] = GlobalData.bookIds.get(i);
        return data;
    }

    @Test(priority = 3, dataProvider = "getbookIds", dependsOnGroups = "AddBook", groups = "GetBookById")
    public void getBookDetailsById(String bookId){
        RequestSpecification getBookByIdRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .pathParams("bookId", bookId);

        Response response = getBookByIdRequest.when()
                .get("Library/GetBook.php?ID={bookId}")
                .then().spec(SpecBuilder.responseSpecification(200))
                .extract().response();

        List<BookDetailsResponse> bookDetailsResponse = Arrays.asList(response.as(BookDetailsResponse[].class));

        log.info("✅ book_name : {}", bookDetailsResponse.get(0).getBookName());
        log.info("✅ isbn : {}", bookDetailsResponse.get(0).getIsbn());
        log.info("✅ aisle : {}", bookDetailsResponse.get(0).getAisle());
        log.info("✅ author : {}", bookDetailsResponse.get(0).getAuthor());
    }

    @Test(priority = 4,dependsOnGroups = "AddBook", groups = "GetBookByAuthorName")
    public void getBookDetailsByAuthorName(){
        RequestSpecification getBookByIdRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .pathParams("authorName", "John foe");

        Response response = getBookByIdRequest.when()
                .get("Library/GetBook.php?AuthorName={authorName}")
                .then().spec(SpecBuilder.responseSpecification(200))
                .extract().response();

        List<BookDetailsResponse> bookDetailsResponse = Arrays.asList(response.as(BookDetailsResponse[].class));
        log.info("✅ book_name : {}", bookDetailsResponse.get(0).getBookName());
        log.info("✅ isbn : {}", bookDetailsResponse.get(0).getIsbn());
        log.info("✅ aisle : {}", bookDetailsResponse.get(0).getAisle());
    }
}
