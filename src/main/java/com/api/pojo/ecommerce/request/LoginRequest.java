package com.api.pojo.ecommerce.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String userEmail;
    private String userPassword;
}
