package com.api.pojo.graphQl.response;


import com.api.pojo.graphQl.model.Data;
import lombok.extern.jackson.Jacksonized;

@lombok.Data
@Jacksonized
public class GraphQLResponse {
    private Data data;
}
