package io.github.gldiazcardenas.commons.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

/**
 * Utility method to manipulate JSON.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class JsonUtils {

    private static ObjectMapper defaultMapper;

    private JsonUtils() {
        super();
    }

    private static ObjectMapper defaultMapper() {
        if (defaultMapper == null) {
            defaultMapper = new ObjectMapper();
            defaultMapper.findAndRegisterModules();
        }
        return defaultMapper;
    }

    private static ObjectMapper objectMapper(ObjectMapper objectMapper) {
        return Optional.ofNullable(objectMapper).orElse(defaultMapper());
    }

    public static <T> T readString(String json, Class<T> type) {
        return readString(json, type, null);
    }

    public static String writeString(Object object) {
        return writeString(object, null);
    }

    public static <T> T readString(String json, Class<T> type, ObjectMapper objectMapper) {
        try {
            return objectMapper(objectMapper).readValue(json, type);
        }
        catch (Exception e) {
            throw ExceptionUtils.toRuntime(e);
        }
    }

    public static String writeString(Object object, ObjectMapper objectMapper) {
        try {
            return objectMapper(objectMapper).writeValueAsString(object);
        }
        catch (Exception e) {
            throw ExceptionUtils.toRuntime(e);
        }
    }

}
