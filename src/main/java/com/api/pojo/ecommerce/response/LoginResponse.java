package com.api.ecommerce.pojo.response;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String userId;
    private String message;
}
