package com.api.ecommerce.tests.api;

import com.api.ecommerce.pojo.request.LoginRequest;
import com.api.ecommerce.pojo.response.LoginResponse;
import com.api.ecommerce.tests.base.BaseTest;
import com.api.ecommerce.utils.ConfigReader;
import com.api.ecommerce.utils.GlobalData;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, groups = "login")
    public void userLogin(){
        RequestSpecification loginRequest = requestJsonPart
                .body(LoginRequest.builder()
                        .userEmail(ConfigReader.get("userEmail"))
                        .userPassword(ConfigReader.get("userPassword"))
                        .build());

        LoginResponse loginResponse = loginRequest.when()
                .post("api/ecom/auth/login")
                .then().extract()
                .response().as(LoginResponse.class);

        GlobalData.authToken = loginResponse.getToken();
        GlobalData.userId = loginResponse.getUserId();

        System.out.println("✅ Token saved: " + GlobalData.authToken);
        System.out.println("✅ User Id generated: " + GlobalData.userId);
        System.out.println("✅ Message: " + loginResponse.getMessage());

    }
}
