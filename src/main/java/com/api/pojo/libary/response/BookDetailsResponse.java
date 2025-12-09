package com.api.pojo.libary.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class BookDetailsResponse {
    @JsonProperty("book_name")
    private String bookName;
    private String isbn;
    private String aisle;
    private String author;
}
