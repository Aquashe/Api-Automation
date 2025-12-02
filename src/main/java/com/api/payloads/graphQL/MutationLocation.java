package com.api.payloads.graphQL;

import com.api.pojo.graphQl.model.Variables;
import com.api.pojo.graphQl.request.GraphQLRequest;

import java.util.List;

public class MutationLocation {

    /**
     *
     * @param locationName String
     * @param locationType String
     * @param locationDimension String
     * @return locationId
     */
    public static GraphQLRequest createLocation(String locationName, String locationType, String locationDimension){
        String mutation = """
                mutation($locationName: String!, $locationType : String!, $locationDimension :String!){
                  createLocation(location:{name:$locationName, type:$locationType, dimension: $locationDimension}){
                    id
                  }
                }
                """;
        Variables variables = Variables.builder()
                .locationName(locationName)
                .locationType(locationType)
                .locationDimension(locationDimension)
                .build();
        GraphQLRequest graphQLRequest = GraphQLRequest.builder()
                .query(mutation)
                .variables(variables)
                .build();
        return graphQLRequest;
    }

    /**
     *
     * @param locationIds Integer
     * @return
     */
    public static GraphQLRequest deleteLocation(List<Integer> locationIds){
        String mutation = """
                mutation($locationIds :[Int!]!){
                    deleteLocations(locationIds :$locationIds){
                      locationsDeleted
                    }
                  }
                """;
        Variables variables = Variables.builder()
                .locationIds(locationIds)
                .build();
        GraphQLRequest graphQLRequest = GraphQLRequest.builder()
                .query(mutation)
                .variables(variables)
                .build();
        return graphQLRequest;

    }

    /**
     *
     * @param locationId String
     * @param locationName String
     * @param locationType String
     * @param locationDimension String
     * @return status Integer
     */
    public static GraphQLRequest ediLocation(Integer locationId, String locationName, String locationType, String locationDimension){
        String mutation = """
                mutation($locationId : Int!, $locationName: String!, $locationType : String!, $locationDimension :String!){
                  editLocation(locationId : $locationId, newLocationData:{name: $locationName, type : $locationType, dimension :$locationDimension}){
                    status
                  }
                }
                """;
        Variables variables = Variables.builder()
                .locationId(locationId)
                .locationName(locationName)
                .locationType(locationType)
                .locationDimension(locationDimension)
                .build();

        GraphQLRequest graphQLRequest = GraphQLRequest.builder()
                .query(mutation)
                .variables(variables)
                .build();
        return graphQLRequest;
    }
}
