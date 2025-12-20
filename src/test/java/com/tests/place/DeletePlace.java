package com.tests.place;

import com.api.pojo.place.request.DeletePlaceRequest;
import com.api.pojo.place.response.DeletePlaceResponse;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class DeletePlace extends ApiBaseTest {

    @DataProvider
    public Object[][] deletePlaceData(){
        return new Object[][]{
                {RuntimeVariable.get("placeId", String.class)}
        };
    }

    @Test(priority = 4, dataProvider = "deletePlaceData", dependsOnGroups = "AddPlace")
    public void deletePlace(String  placeId){
        RequestSpecification deletePlaceRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .pathParams("key", "qaclick123")
                .body(DeletePlaceRequest.builder().place_id(placeId).build());

        Response response = deletePlaceRequest.when()
                .delete("maps/api/place/delete/json?key={key}")
                .then().spec(Spec_Builder.responseSpecification(200))
                .extract().response();

        DeletePlaceResponse deletePlaceResponse = response.as(DeletePlaceResponse.class);
        log.info("✅ status  : {}",deletePlaceResponse.getStatus());
    }
}
