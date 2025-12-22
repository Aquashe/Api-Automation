package com.resources.ecommerce;

import com.resources.core.ApiResource;

public enum EcommerceApiResources implements ApiResource {
    LoginAPI("api/ecom/auth/login"),
    CreateProductAPI("api/ecom/product/add-product"),
    CreateOrderAPI("api/ecom/order/create-order"),
    GetOrderAPI("api/ecom/order/get-orders-details"),
    DeleteOrderAPI("api/ecom/order/delete-order/{orderId}"),
    DeleteProductAPI("api/ecom/product/delete-product/{productId}");

    private String resource;

    EcommerceApiResources(String resourceName){
        this.resource = resourceName;
    }

    @Override
    public String getResource() {
        return resource;
    }
}
