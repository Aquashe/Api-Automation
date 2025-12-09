package com.tests.library;

import com.api.pojo.libary.request.DeleteBookRequest;
import com.api.pojo.libary.response.DeleteBookResponse;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class DeleteBook extends ApiBaseTest {

    @DataProvider
    public Object[][] getbookIds(){
        Object[][] data = new Object[GlobalData.bookIds.size()][1];
        for(int i=0; i<GlobalData.bookIds.size(); i++)
            data[i][0] = GlobalData.bookIds.get(i);
        return data;
    }

    @Test(priority = 5, dataProvider = "getbookIds", dependsOnGroups = "AddBook", groups = "DeleteBook")
    public void deleteBookById(String bookId){
        RequestSpecification deleteBookRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(DeleteBookRequest.builder().id(bookId).build());

        Response response = deleteBookRequest.when()
                .delete("Library/DeleteBook.php")
                .then()
                .spec(Spec_Builder.responseSpecification(200))
                .extract().response();

        DeleteBookResponse deleteBookResponse = response.as(DeleteBookResponse.class);
        log.info("✅ msg : {}  with id : {}", deleteBookResponse.getMsg(), bookId);
    }
}
