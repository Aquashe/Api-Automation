package com.tests.place;

import com.api.pojo.place.response.GetPlaceResponse;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class GetPlace extends ApiBaseTest {

    @DataProvider
    public Object[][] getPlaceData(){
        return new Object[][]{
                {RuntimeVariable.get("placeId", String.class)}
        };
    }

    @Test(priority = 2, dataProvider = "getPlaceData", dependsOnGroups = "AddPlace", groups = "GetPlace")
    public void getPlaceDetails(String placeId){
        RequestSpecification getPlaceRequest = given()
                .spec(Spec_Builder.noBodyPartRequestSpecification("baseUrl"))
                .pathParams("key", "qaclick123")
                .pathParams("placeId", placeId);

        Response response = getPlaceRequest.when()
                .get("maps/api/place/get/json?place_id={placeId}&key={key}")
                .then().spec(Spec_Builder.responseSpecification(200))
                .extract().response();

        GetPlaceResponse getPlaceResponse = response.as(GetPlaceResponse.class);
        log.info("✅ location : {\n\tlatitude : {} \tlongitude : {}}", getPlaceResponse.getLocation().getLatitude(), getPlaceResponse.getLocation().getLongitude());
        log.info("✅ accuracy  : {}", getPlaceResponse.getAccuracy());
        log.info("✅ name : {}", getPlaceResponse.getName());
        log.info("✅ phone_number  : {}", getPlaceResponse.getPhone_number());
        log.info("✅ address : {}", getPlaceResponse.getAddress());
        log.info("✅ types : {}", getPlaceResponse.getTypes());
        log.info("✅ website  : {}", getPlaceResponse.getWebsite());
        log.info("✅ language : {}", getPlaceResponse.getLanguage());
    }
}
