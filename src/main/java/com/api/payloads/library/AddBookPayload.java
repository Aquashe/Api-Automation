package com.api.payloads.library;

import com.api.pojo.libary.request.AddBookRequest;

public class AddBookPayload {
    public static AddBookRequest addBookRequestData(String name, String isbn, String aisle, String authorName){
        return AddBookRequest.builder()
                .name(name)
                .isbn(isbn)
                .aisle(aisle)
                .author(authorName)
                .build();
    }
}
