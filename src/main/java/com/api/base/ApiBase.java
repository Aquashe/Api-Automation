package com.api.base;

import com.api.files.SpecBuilder;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class ApiBase {

    protected static RequestSpecification requestJsonPart;
    protected static RequestSpecification requestMultiPart;
    protected static RequestSpecification requestNoBodyPart;


    static {
        requestJsonPart = given().spec(SpecBuilder.jsonPartRequestSpecification());
        requestMultiPart = given().spec(SpecBuilder.multiPartRequestSpecification());
        requestNoBodyPart = given().spec(SpecBuilder.noBodyPartRequestSpecification());
    }
}
