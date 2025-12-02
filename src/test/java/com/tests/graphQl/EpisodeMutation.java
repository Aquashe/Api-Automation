package com.tests.graphQl;

import com.api.payloads.graphQL.MutationEpisode;
import com.api.pojo.graphQl.response.GraphQLResponse;
import com.api.utils.GlobalData;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.api.utils.GlobalData.priorityLast;
import static io.restassured.RestAssured.given;

public class EpisodeMutation extends ApiBaseTest {

    @DataProvider
    public Object[][] createEpisodeData(){
        return  new Object[][]{
                {"Sundari", "12-20-23", "10"},
                {"Parasparam", "12-20-10", "11"},
                {"Parichatham", "12-20-12", "12"},
                {"SthiriDhanam", "12-20-13", "13"}
        };
    }

    @Test(dataProvider = "createEpisodeData", priority = 7, groups = "createEpisode")
    public void createEpisode(String episodeName, String air_date, String episodeCustomId){
        RequestSpecification createCharacterRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(MutationEpisode.createEpisode(episodeName, air_date, episodeCustomId));

        Response response = createCharacterRequest
                .when().post("gq/graphql")
                .then().log().all().extract().response();

        GraphQLResponse graphQLResponse = response.as(GraphQLResponse.class);
        GlobalData.episodeId = graphQLResponse.getData().getCreateEpisode().getId();
        GlobalData.episodeIds.add(GlobalData.episodeId);

        System.out.println("✅ Episode created: " +GlobalData.episodeId);
    }

    @Test(priority = 8, dependsOnGroups = "createEpisode")
    public void printEpisodeIds(){
        for(Integer id :GlobalData.episodeIds){
            System.out.print("\t"+id);
        }
    }

    @DataProvider
    public Object[][] associateEpisodeCharacterData(){
        Object[][] data = new Object[GlobalData.episodeIds.size()][2];
        for(int i=0; i<GlobalData.episodeIds.size(); i++){
            data[i][0] = GlobalData.episodeIds.get(i);
            data[i][1] = GlobalData.characterIds.get(i);
        }
        return data;
    }
    @Test(dataProvider = "associateEpisodeCharacterData", priority = 9, dependsOnGroups = {"createEpisode", "createCharacter"})
    public void associateEpisodeCharacter(Integer episodeId, Integer characterId){
        RequestSpecification createCharacterRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(MutationEpisode.associateEpisodeCharacter(episodeId, characterId));

        Response response = createCharacterRequest
                .when().post("gq/graphql")
                .then().log().all().extract().response();

        GraphQLResponse graphQLResponse = response.as(GraphQLResponse.class);
        System.out.println("✅ Associate episode character: " +graphQLResponse.getData().getAssociateEpisodeCharacter().getStatus());
    }


    @Test(priority = GlobalData.priorityLast, dependsOnGroups = "createEpisode", groups = "deleteEpisode", enabled = GlobalData.enabled)
    public void deleteEpisode(){
        RequestSpecification createCharacterRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(MutationEpisode.deleteEpisode(GlobalData.episodeIds));

        Response response = createCharacterRequest
                .when().post("gq/graphql")
                .then().log().all().extract().response();

        GraphQLResponse graphQLResponse = response.as(GraphQLResponse.class);

        System.out.println("✅ Episode deleted: " +graphQLResponse.getData().getDeleteEpisodes().getEpisodesDeleted());
    }
}
