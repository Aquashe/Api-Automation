package com.tests.place;

import com.api.payloads.place.AddPlacePayload;
import com.api.pojo.place.response.AddPlaceResponse;
import com.api.utils.RuntimeVariable;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class AddPlace extends ApiBaseTest {

    @DataProvider
    public Object[][] addPlaceData() {
        return new Object[][]{
                {-38.383494, 33.427362, 50, "Frontline house", "(+91) 983 893 3937", "29, side layout, cohen 09", "http://google.com",
                        List.of("shoe park", "shop"), "French-IN"}
        };
    }

    @Test(priority = 1, dataProvider = "addPlaceData", groups = "AddPlace")
    public void createPlace(double latitude, double longitude, int accuracy, String houseName, String phoneNumber, String address, String website,
                            List<String> types, String language) {

        RequestSpecification addPlaceRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .pathParams("key", "qaclick123")
                .body(AddPlacePayload.settingAddPlaceBody(latitude, longitude, accuracy, houseName, phoneNumber, address, website, types,language));

        Response response = addPlaceRequest.when()
                .post("maps/api/place/add/json?key={key}")
                .then().spec(Spec_Builder.responseSpecification(200))
                .extract().response();

        AddPlaceResponse addPlaceResponse = response.as(AddPlaceResponse.class);
        RuntimeVariable.set("placeId", addPlaceResponse.getPlace_id());
        log.info("✅ status : {}", addPlaceResponse.getStatus());
        log.info("✅ place_id  : {}", RuntimeVariable.get("placeId", String.class));
        log.info("✅ scope : {}", addPlaceResponse.getScope());
        log.info("✅ reference  : {}", addPlaceResponse.getReference());
        log.info("✅ id : {}", addPlaceResponse.getId());
    }
}
