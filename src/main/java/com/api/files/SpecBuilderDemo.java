package com.api.files;


import com.api.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SpecBuilderDemo {

    protected static final PrintStream printStream;
    static {
        try {
            printStream = new PrintStream(new FileOutputStream("logging.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static RequestSpecification jsonPartRequestSpecification() {
        return new RequestSpecBuilder().setBaseUri(ConfigReader.get("baseUrl"))
                .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification multiPartRequestSpecification() {
        return new RequestSpecBuilder().setBaseUri(ConfigReader.get("baseUrl"))
                .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                .setContentType("multipart/form-data")
                .build();
    }

    public static RequestSpecification noBodyPartRequestSpecification() {
        return new RequestSpecBuilder().setBaseUri(ConfigReader.get("baseUrl"))
                .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                .build();
    }

    public static ResponseSpecification responseSpecification(int statusCode) {
        return new ResponseSpecBuilder().expectStatusCode(statusCode)
                .expectContentType(ContentType.JSON)
                .build();
    }
}