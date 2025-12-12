package com.api.ecommerce.pojo.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class CreateOrderResponse {
    private List<String> orders;
    private List<String> productOrderId;
    private String message;
}
