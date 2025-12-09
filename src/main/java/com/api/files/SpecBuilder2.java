package com.api.files;


import com.api.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SpecBuilder2 {

    protected static final PrintStream printStream;
    static {
        try {
            printStream = new PrintStream(new FileOutputStream("logging.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public RequestSpecification jsonPartRequestSpecification(String baseUrl) {
        return new RequestSpecBuilder().setBaseUri(ConfigReader.get(baseUrl))
                .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                .setContentType(ContentType.JSON)
                .build().log().all();
    }

    public RequestSpecification multiPartRequestSpecification(String baseUrl) {
        return new RequestSpecBuilder().setBaseUri(ConfigReader.get(baseUrl))
                .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                .setContentType("multipart/form-data")
                .build().log().all();
    }

    public RequestSpecification noBodyPartRequestSpecification(String baseUrl) {
        return new RequestSpecBuilder().setBaseUri(ConfigReader.get(baseUrl))
                .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                .build().log().all();
    }

    public ResponseSpecification responseSpecification(int statusCode) {
        return new ResponseSpecBuilder().expectStatusCode(statusCode)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}