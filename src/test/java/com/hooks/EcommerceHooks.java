package com.hooks;

import com.api.exceptions.ApiException;
import com.api.pojo.ecommerce.model.OrderData;
import com.api.utils.RuntimeVariable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EcommerceHooks {

    @After("@UserLogin")
    public void afterUserLogin() {
        log.info("✅ token       : {} ", RuntimeVariable.get("token", String.class));
        log.info("✅ userId      : {} ", RuntimeVariable.get("userId", String.class));
        log.info("✅ message     : {} ", RuntimeVariable.get("message", String.class));
    }

    @Before("@CreateProduct")
    public void beforeCreateProduct() {
        if (RuntimeVariable.get("token", String.class).isEmpty())
            throw new ApiException("❌ Authorization token needed for Create product");
        if (RuntimeVariable.get("userId", String.class).isEmpty())
            throw new ApiException("❌ UserId needed for Create product");
    }

    @After("@CreateProduct")
    public void afterCreateProduct() {
        log.info("✅ productId    : {} ", RuntimeVariable.get("productId", String.class));
        log.info("✅ message      : {} ", RuntimeVariable.get("productCreateMsg", String.class));
    }

    @Before("@DeleteProduct")
    public void beforeDeleteProduct() {
        if (RuntimeVariable.get("token", String.class).isEmpty())
            throw new ApiException("❌ Authorization token needed for Delete product");
        if (RuntimeVariable.get("productId", String.class).isEmpty())
            throw new ApiException("❌ ProductId needed for Delete product");
    }

    @After("@DeleteProduct")
    public void afterDeleteProduct() {
        log.info("✅ message      : {} ", RuntimeVariable.get("productDeleteMsg", String.class));
    }

    @Before("@CreateOrder")
    public void beforeCreateOrder() {
        if (RuntimeVariable.get("token", String.class).isEmpty())
            throw new ApiException("❌ Authorization token needed for Create Order");
    }

    @After("@CreateOrder")
    public void afterCreateOrder() {
        log.info("✅ orders           : {} ", RuntimeVariable.get("orderId", String.class));
        log.info("✅ productOrderId   : {} ", RuntimeVariable.get("ordersProductId", String.class));
        log.info("✅ message          : {} ", RuntimeVariable.get("orderMessage", String.class));
    }

    @Before("@DeleteOrder")
    public void beforeDeleteOrder() {
        if (RuntimeVariable.get("token", String.class).isEmpty())
            throw new ApiException("❌ Authorization token needed for Delete Order");
        if (RuntimeVariable.get("orderId", String.class).isEmpty())
            throw new ApiException("❌ OrderId needed for Delete Order");
    }

    @After("@DeleteOrder")
    public void afterDeleteOrder() {
        log.info("✅ message : {} ", RuntimeVariable.get("deleteOrderMessage", String.class));
    }

    @Before("@GetOrder")
    public void beforeGetOrder() {
        if (RuntimeVariable.get("token", String.class).isEmpty())
            throw new ApiException("❌ Authorization token needed for Get Order");
    }

    @After("@GetOrder")
    public void afterGetOrder() {
        RuntimeVariable.get("orderData", OrderData.class);
        log.info("✅ _id                 : {} ", RuntimeVariable.get("orderData", OrderData.class).get_id());
        log.info("✅ orderById           : {} ", RuntimeVariable.get("orderData", OrderData.class).getOrderById());
        log.info("✅ orderBy             : {} ", RuntimeVariable.get("orderData", OrderData.class).getOrderBy());
        log.info("✅ productOrderedId    : {} ", RuntimeVariable.get("orderData", OrderData.class).getProductOrderedId());
        log.info("✅ productName         : {} ", RuntimeVariable.get("orderData", OrderData.class).getProductName());
        log.info("✅ country             : {} ", RuntimeVariable.get("orderData", OrderData.class).getCountry());
        log.info("✅ productDescription  : {} ", RuntimeVariable.get("orderData", OrderData.class).getProductDescription());
        log.info("✅ productImage        : {} ", RuntimeVariable.get("orderData", OrderData.class).getProductImage());
        log.info("✅ orderPrice          : {} ", RuntimeVariable.get("orderData", OrderData.class).getOrderPrice());
        log.info("✅ __v                 : {} ", RuntimeVariable.get("orderData", OrderData.class).get__v());
        log.info("✅ message             : {} ", RuntimeVariable.get("getOrderMessage", String.class));
    }
}
