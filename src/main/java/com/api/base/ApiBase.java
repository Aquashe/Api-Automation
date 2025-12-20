package com.api.base;

import com.api.files.SpecBuilderDemo;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class ApiBase {

    protected static RequestSpecification requestJsonPart;
    protected static RequestSpecification requestMultiPart;
    protected static RequestSpecification requestNoBodyPart;


    static {
        requestJsonPart = given().spec(SpecBuilderDemo.jsonPartRequestSpecification());
        requestMultiPart = given().spec(SpecBuilderDemo.multiPartRequestSpecification());
        requestNoBodyPart = given().spec(SpecBuilderDemo.noBodyPartRequestSpecification());
    }
}
