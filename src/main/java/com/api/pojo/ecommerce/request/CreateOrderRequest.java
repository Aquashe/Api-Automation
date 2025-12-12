package com.api.ecommerce.pojo.request;

import com.api.ecommerce.pojo.model.OrderDetails;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CreateOrderRequest {
    private List<OrderDetails> orders;
}
