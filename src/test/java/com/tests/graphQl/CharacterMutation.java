package com.tests.graphQl;

import com.api.payloads.graphQL.MutationCharacter;
import com.api.pojo.graphQl.response.GraphQLResponse;
import com.api.utils.GlobalData;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.api.utils.GlobalData.priorityLast;
import static io.restassured.RestAssured.given;

public class CharacterMutation extends ApiBaseTest {

    @DataProvider
    public Object[][] createCharacterData(){
        return new Object[][]{
                {"Babloo", "Fool", "Died", "Home", "Men", "png", 25851, 25859},
                {"Mottu", "Patlu", "Died", "Homo", "Men", "png", 25852, 25860},
                {"Chttukki", "Brave", "Alive", "Homo", "Woman", "png", 25853, 25861},
                {"Tuntu", "Calm", "Alive", "Homo", "Women", "png", 25854, 25862}
        };
    }
    @Test(dataProvider = "createCharacterData", priority = 4, dependsOnGroups = {"createLocation", "editLocation"}, groups = "createCharacter")
    public void createCharacter(
            String characterName,
            String characterType,
            String characterStatus,
            String species,
            String gender,
            String image,
            Integer originId,
            Integer locationId){

        RequestSpecification createCharacterRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(MutationCharacter.createCharacter(characterName, characterType, characterStatus,species,gender, image,
                        originId, locationId));

        Response response = createCharacterRequest
                .when().post("gq/graphql")
                .then().log().all().extract().response();

        GraphQLResponse graphQLResponse = response.as(GraphQLResponse.class);
        GlobalData.characterId = graphQLResponse.getData().getCreateCharacter().getId();
        GlobalData.characterIds.add(GlobalData.characterId);

        System.out.println("✅ Location deleted: " +GlobalData.characterId);
    }

    @Test(priority = 5, dependsOnGroups = "createCharacter")
    public void printCharacterIds(){
        for(Integer id :GlobalData.characterIds){
            System.out.print("\t"+id);
        }
    }

    @Test(priority = priorityLast, dependsOnGroups = {"createCharacter", "deleteEpisode"},groups = "deleteCharacter", enabled = GlobalData.enabled)
    public void deleteCharacter(){
        RequestSpecification createCharacterRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(MutationCharacter.deleteCharacter(GlobalData.characterIds));

        Response response = createCharacterRequest
                .when().post("gq/graphql")
                .then().log().all().extract().response();

        GraphQLResponse graphQLResponse = response.as(GraphQLResponse.class);
        System.out.println("✅ Characters deleted: " +graphQLResponse.getData().getDeleteCharacters().getCharactersDeleted());
    }
}
