package com.api.ecommerce.pojo.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String userEmail;
    private String userPassword;
}
