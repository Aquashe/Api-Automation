package com.api.ecommerce.pojo.response;


import com.api.ecommerce.pojo.model.OrderData;
import lombok.Data;

@Data
public class GetOrderResponse {
    private OrderData data;
    private String message;
}
