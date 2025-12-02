package com.tests.graphQl;

import com.api.payloads.graphQL.MutationLocation;
import com.api.pojo.graphQl.response.GraphQLResponse;
import com.api.utils.GlobalData;
import com.base.ApiBaseTest;

import static com.api.utils.GlobalData.priorityLast;
import static io.restassured.RestAssured.given;

import io.cucumber.java.sl.In;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LocationsMutation extends ApiBaseTest {

    @DataProvider
    public Object[][] createLocationData() {
        return new Object[][] {
                {"NewZealand", "SouthZone", "234"},
                {"Australia", "NorthZone", "235"},
                {"NewYork", "EastZone", "236"},
                {"Los Angles", "WestZone", "237"}
        };
    }
    @Test(dataProvider = "createLocationData", priority = 1,groups = "createLocation")
    public void createLocation(String locationName, String locationType, String locationDimension){
       RequestSpecification queryRequest = given()
               .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(MutationLocation.createLocation(locationName, locationType, locationDimension));

        Response response = queryRequest
                .when().post("gq/graphql")
                .then().log().all().extract().response();

        GraphQLResponse graphQLResponse = response.as(GraphQLResponse.class);
        GlobalData.locationId = graphQLResponse.getData().getCreateLocation().getId();
        GlobalData.locationIds.add(GlobalData.locationId);
        System.out.println("✅ Location created: " + GlobalData.locationId);
    }

    @Test(priority = 2, dependsOnGroups = "createLocation")
    public void printLocationIds(){
        for(Integer id :GlobalData.locationIds){
            System.out.print("\t"+id);
        }
    }

    @DataProvider
    public Object[][] editLocationData(){
        return new Object[][]{
                {GlobalData.locationId,"NewZealand", "SouthZone", "234"},
        };
    }
    @Test(dataProvider = "editLocationData", priority = 3, dependsOnGroups = "createLocation", groups = "editLocation")
    public static void editLocation(Integer locationId, String locationName, String locationType, String locationDimension){
        RequestSpecification queryRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(MutationLocation.ediLocation(locationId,locationName, locationType, locationDimension));
        Response response = queryRequest
                .when().post("gq/graphql")
                .then().log().all().extract().response();

        GraphQLResponse graphQLResponse = response.as(GraphQLResponse.class);
        System.out.println("✅ Location edited: " + graphQLResponse.getData().getEditLocation().getStatus());
    }

    @Test(priority = priorityLast, dependsOnGroups = {"createLocation", "deleteCharacter"}, groups = "deleteLocation", enabled = GlobalData.enabled)
    public void deleteLocation(){
        RequestSpecification deleteMutationRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(MutationLocation.deleteLocation(GlobalData.locationIds));

        Response response = deleteMutationRequest
                .when().post("gq/graphql")
                .then().log().all().extract().response();

        GraphQLResponse graphQLResponse = response.as(GraphQLResponse.class);
        System.out.println("✅ Location deleted: " + graphQLResponse.getData().getDeleteLocations().getLocationsDeleted());
    }
}
