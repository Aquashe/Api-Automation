package com.base;


import com.api.files.SpecBuilder2;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiBaseTest {
    protected static SpecBuilder2 Spec_Builder =  new SpecBuilder2();

    static {
        RestAssured.useRelaxedHTTPSValidation();
    }
    protected static RequestSpecification given() {
        return RestAssured.given();
    }
}
