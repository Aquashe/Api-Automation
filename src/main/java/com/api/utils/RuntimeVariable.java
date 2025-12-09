package com.api.utils;

import java.util.HashMap;
import java.util.Map;

public class RuntimeVariable {
    private static  Map<String, Object> data = new HashMap<>();

    public static void set(String key , Object value){
        data.put(key, value);
    }

    public static Object get(String key){
        return data.get(key);
    }
}
