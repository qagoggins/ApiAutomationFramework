package io.framework.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class Converter {
    public static Map<String, Object> jsonResponseToMap(Response response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {};
        return objectMapper.readValue(response.prettyPrint(), typeReference);
    }
}
