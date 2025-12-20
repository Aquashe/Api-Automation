package com.tests.place;

import com.api.pojo.place.request.UpdatePlaceRequest;
import com.api.pojo.place.response.UpdatePlaceResponse;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class UpdatePlace extends ApiBaseTest {

    @DataProvider
    public Object[][] updatePlaceData() {
        return new Object[][]{
                {RuntimeVariable.get("placeId", String.class), "70 Summer walk, Florida", "qaclick123",}
        };
    }

    @Test(priority = 3, dataProvider = "updatePlaceData", dependsOnGroups = "AddPlace", groups = "UpdatePlace")
    public void updatePlaceDetails(String placeId, String address, String key) {
        RequestSpecification updatePlaceRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(UpdatePlaceRequest.builder().place_id(placeId).address(address).key(key).build());

        Response response = updatePlaceRequest.when()
                .put("maps/api/place/update/json")
                .then().spec(Spec_Builder.responseSpecification(200))
                .extract().response();

        UpdatePlaceResponse updatePlaceResponse = response.as(UpdatePlaceResponse.class);
        log.info("✅ msg  : {}", updatePlaceResponse.getMsg());
    }
}
