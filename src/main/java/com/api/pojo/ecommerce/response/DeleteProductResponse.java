package com.api.pojo.ecommerce.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class DeleteProductResponse {
    private String message;
}
