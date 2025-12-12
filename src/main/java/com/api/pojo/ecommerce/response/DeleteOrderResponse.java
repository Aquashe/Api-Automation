package com.api.ecommerce.pojo.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class DeleteOrderResponse {
    private String message;
}
