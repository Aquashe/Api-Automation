package com.api.pojo.graphQl.request;

import com.api.pojo.graphQl.model.Variables;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GraphQLRequest {
    private String query;
    private Variables variables;
}
