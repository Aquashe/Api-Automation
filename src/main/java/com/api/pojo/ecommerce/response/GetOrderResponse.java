package com.api.pojo.ecommerce.response;


import com.api.pojo.ecommerce.model.OrderData;
import lombok.Data;

@Data
public class GetOrderResponse {
    private OrderData data;
    private String message;
}
