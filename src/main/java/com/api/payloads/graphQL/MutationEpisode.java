package com.api.payloads.graphQL;

import com.api.pojo.graphQl.model.Variables;
import com.api.pojo.graphQl.request.GraphQLRequest;

import java.util.List;

public class MutationEpisode {
    public static GraphQLRequest createEpisode(String episodeName, String air_date, String episodeCustomId){
       String mutation = """
               mutation($episodeName: String!, $air_date : String!,$episodeCustomId: String!){
                 createEpisode(episode:{name:$episodeName, air_date:$air_date,episode:$episodeCustomId}){
                   id
                 }
               }
               """;
        Variables variables = Variables.builder()
                .episodeName(episodeName)
                .air_date(air_date)
                .episodeCustomId(episodeCustomId)
                .build();
        GraphQLRequest graphQLRequest = GraphQLRequest.builder()
                .query(mutation)
                .variables(variables)
                .build();
        return graphQLRequest;

    }

    public static GraphQLRequest deleteEpisode(List<Integer> episodeIds){
        String mutation = """
                mutation($episodeIds :[Int!]!){
                  deleteEpisodes(episodeIds:$episodeIds){
                    episodesDeleted
                  }
                }
                """;
        Variables variables = Variables.builder()
                .episodeIds(episodeIds)
                .build();
        GraphQLRequest graphQLRequest = GraphQLRequest.builder()
                .query(mutation)
                .variables(variables)
                .build();
        return graphQLRequest;
    }

    public static GraphQLRequest associateEpisodeCharacter(Integer episodeId, Integer characterId){
        String mutation = """
                mutation($episodeId :Int!, $characterId: Int!){
                  associateEpisodeCharacter(episodeId:$episodeId, characterId:$characterId){
                    status
                  }
                }
                """;
        Variables variables = Variables.builder()
                .episodeId(episodeId)
                .characterId(characterId)
                .build();
        GraphQLRequest graphQLRequest = GraphQLRequest.builder()
                .query(mutation)
                .variables(variables)
                .build();
        return graphQLRequest;
    }
}
