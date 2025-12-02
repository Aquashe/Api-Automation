package com.api.utils;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class ResponseValidator {
    public static void assertFieldValue(Object responsePojo, String key, String value){
        try {
            Field field = responsePojo.getClass().getDeclaredField(key);
            field.setAccessible(true);
            assertEquals(value, field.get(responsePojo));
        }catch (Exception e){
            fail("Failed to assert " + key + " :" + e.getMessage());
        }
    }
}
