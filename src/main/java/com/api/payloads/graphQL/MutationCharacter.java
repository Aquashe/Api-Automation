package com.api.payloads.graphQL;

import com.api.pojo.graphQl.model.Variables;
import com.api.pojo.graphQl.request.GraphQLRequest;

import java.util.List;

public class MutationCharacter {
    public static GraphQLRequest createCharacter(
            String characterName,
            String characterType,
            String characterStatus,
            String species,
            String gender,
            String image,
            Integer originId,
            Integer locationId){

        String mutation = """
                mutation($characterName: String!, $characterType: String!,\s
                  $characterStatus: String!, $species: String!,
                  $gender: String!, $image: String!, $originId: Int!,$locationId : Int!){
                  createCharacter(character:{name:$characterName, type:$characterType,
                    status:$characterStatus,species :$species, gender:$gender,
                    image:$image, originId:$originId, locationId:$locationId}){
                    id
                  }
                }
                """;
        Variables variables = Variables.builder()
                .characterName(characterName)
                .characterType(characterType)
                .characterStatus(characterStatus)
                .species(species)
                .gender(gender)
                .image(image)
                .originId(originId)
                .locationId(locationId)
                .build();
        GraphQLRequest graphQLRequest = GraphQLRequest.builder()
                .query(mutation)
                .variables(variables)
                .build();
        return graphQLRequest;
    }

    public static GraphQLRequest deleteCharacter(List<Integer> characterIds){
        String mutation = """
                mutation($characterIds: [Int!]!){
                  deleteCharacters(characterIds : $characterIds){
                    charactersDeleted
                  }
                }
                """;
        Variables variables = Variables.builder()
                .characterIds(characterIds)
                .build();

        GraphQLRequest graphQLRequest = GraphQLRequest.builder()
                .query(mutation)
                .variables(variables)
                .build();
        return graphQLRequest;
    }
}
