package com.tests.library;

import com.api.payloads.library.AddBookPayload;
import com.api.pojo.excel.model.CellPosition;
import com.api.pojo.libary.request.AddBookRequest;
import com.api.pojo.libary.response.AddBookResponse;
import com.api.utils.DataDriven;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.cucumber.java.sl.In;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AddBook extends ApiBaseTest {

    private static final String EXCEL_PATH =
            "C:\\Users\\Wonder\\OneDrive\\Desktop\\ApiFramework\\ExcelDemoData.xlsx";
    private static final String SHEET_NAME = "Library";

    @DataProvider 
    public Object[][] addBookData(){
        return new Object[][]{
                {"Learn Appium Automation with Java", "bcd", "227", "John foe"      ,},
                {"Learn Web Automation with Java"   , "efg", "228", "John Brittass" , },
                {"Learn Api Automation with Java"   , "hij", "229", "John Federy"   ,},
        };
    }

    @DataProvider
    public Object[][] addBookDataFromExcel(){

        XSSFSheet sheet = DataDriven.fetchSheetDataFromExcel(EXCEL_PATH, SHEET_NAME);
        List<Object> nameList = DataDriven.getWholeRowValuesByRowName(sheet, "name");
        List<Object> isbnList = DataDriven.getWholeRowValuesByRowName(sheet, "isbn");
        List<Object> aisleList = DataDriven.getWholeRowValuesByRowName(sheet, "aisle");
        List<Object> authorList = DataDriven.getWholeRowValuesByRowName(sheet, "author");

        Object[][] excelData = new Object[nameList.size()-1][4];
        for(int i=0; i<nameList.size() -1; i++){
            excelData[i][0] = nameList.get(i+1).toString();
            excelData[i][1] = isbnList.get(i+1).toString();
            excelData[i][2] = aisleList.get(i+1).toString();
            excelData[i][3] = authorList.get(i+1).toString();
        }

        return excelData;
    }

    @Test(priority = 1, dataProvider = "addBookDataFromExcel", groups = "AddBook")
    public void createBook(String name, String isbn, String aisle, String authorName){
        RequestSpecification createBookRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .header("Content-Type", "application/json")
                .body(AddBookPayload.addBookRequestData(name, isbn, aisle, authorName));

        Response response = createBookRequest.when()
                .post("Library/Addbook.php")
                .then().log().all().extract().response();

        AddBookResponse addBookResponse = response.as(AddBookResponse.class);
        RuntimeVariable.set("bookId", addBookResponse.getId());
        GlobalData.bookIds.add(RuntimeVariable.get("bookId").toString());

        log.info("✅ Msg : {}", addBookResponse.getMsg());
        log.info("✅ ID  : {}", RuntimeVariable.get("bookId"));
    }


    @Test(priority = 2,dependsOnGroups = "AddBook")
    public void printBookIds(){
        GlobalData.bookIds.forEach(bookId -> System.out.println(bookId));
    }

}
