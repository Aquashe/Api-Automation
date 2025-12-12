package com.tests.ecommerce;

import com.api.pojo.ecommerce.request.LoginRequest;
import com.api.pojo.ecommerce.response.LoginResponse;
import com.api.utils.ConfigReader;
import com.api.utils.GlobalData;
import com.base.ApiBaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class LoginTest extends ApiBaseTest {

    @Test(priority = 1, groups = "login")
    public void userLogin(){
        RequestSpecification loginRequest = given()
                .spec(Spec_Builder.jsonPartRequestSpecification("baseUrl"))
                .body(LoginRequest.builder()
                        .userEmail(ConfigReader.get("userEmail"))
                        .userPassword(ConfigReader.get("userPassword"))
                        .build());

        Response response = loginRequest.when()
                .post("api/ecom/auth/login")
                .then()
                .spec(Spec_Builder.responseSpecification(200))
                .extract()
                .response();

        LoginResponse loginResponse = response.as(LoginResponse.class);
        GlobalData.authToken = loginResponse.getToken();
        GlobalData.userId = loginResponse.getUserId();

        log.info("✅ Token saved: {}" , GlobalData.authToken);
        log.info("✅ User Id generated: {}", GlobalData.userId);
        log.info("✅ Message:{} ", loginResponse.getMessage());

    }
}
