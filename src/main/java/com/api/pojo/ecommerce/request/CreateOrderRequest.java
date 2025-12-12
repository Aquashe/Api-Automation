package com.api.pojo.ecommerce.request;


import com.api.pojo.ecommerce.model.OrderDetails;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CreateOrderRequest {
    private List<OrderDetails> orders;
}
