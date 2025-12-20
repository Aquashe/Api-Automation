package com.base;


import com.api.files.SpecBuilder;
import com.resources.core.ApiResource;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiBaseTest {
    protected static SpecBuilder Spec_Builder =  new SpecBuilder();

    static {
        RestAssured.useRelaxedHTTPSValidation();
    }
    protected static RequestSpecification given() {
        return RestAssured.given();
    }
    public static void equals(Object actual, Object expected) {
        org.testng.Assert.assertEquals(actual, expected);
    }

    public Response setHttpMethodWithRequestSpecification(
            RequestSpecification req,
            String httpMethod,
            ApiResource api
    ) {
        switch (httpMethod.toUpperCase()) {
            case "POST":
                return req.when().post(api.getResource());
            case "GET":
                return req.when().get(api.getResource());
            case "PUT":
                return req.when().put(api.getResource());
            case "DELETE":
                return req.when().delete(api.getResource());
            default:
                throw new IllegalArgumentException("Unsupported HTTP Method: " + httpMethod);
        }
    }

}
