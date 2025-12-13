package com.resources.core;

import com.api.exceptions.ApiException;

public class ApiResourceResolver {
    private static final Class<? extends Enum<?>>[] API_ENUMS = new Class[]{
            com.resources.library.BookApiResources.class,
    };

    public static ApiResource resolve(String resourceName) {

        for (Class<? extends Enum<?>> enumClass : API_ENUMS) {
            try {
                Enum<?> enumConstant = Enum.valueOf(
                        (Class) enumClass,
                        resourceName
                );
                return (ApiResource) enumConstant;

            } catch (IllegalArgumentException ignored) {}
        }
        throw new ApiException("❌ Invalid API Resource: " + resourceName);
    }
}
