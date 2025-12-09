package com.api.pojo.libary.response;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class DeleteBookResponse {
    private String msg;
}
