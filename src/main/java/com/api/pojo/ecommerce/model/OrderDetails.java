package com.api.ecommerce.pojo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderDetails {
    private String country;
    private String productOrderedId;
}
