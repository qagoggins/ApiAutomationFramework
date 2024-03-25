package io.framework.Assertions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Assertions {
    private static Logger logger = LogManager.getLogger(Assertions.class);

    public static void assertResponseFieldType(Map<String, Object> response, String fieldName, Class<?> expectedType) {
        if(!response.containsKey(fieldName)) {
            String errorMsg = "Field '"+fieldName+"' not found in the response";
            logger.error(errorMsg);
            throw new AssertionError(errorMsg);
        }
        Object fieldValue = response.get(fieldName);
        if(!expectedType.isInstance(fieldValue)) {
            String errorMsg = "Field '"+fieldName+"' has type '"+fieldValue.getClass().getSimpleName()+"' expected '"+expectedType.getSimpleName()+"'.";
            logger.error(errorMsg);
            throw new AssertionError(errorMsg);
        }
    }
    public static void assertResponseHasKeys(Map<String, Object> response,String... expectedKeys) {
        if(response.isEmpty()) {
            String errorMsg = "Response has no fields inside";
            logger.error(errorMsg);
            throw new AssertionError(errorMsg);
        }
        for(String key : expectedKeys) {
            if(!response.containsKey(key)) {
                String errorMsg = "Response does not contain expected key: '"+key+"'";
                logger.error(errorMsg);
                throw new AssertionError(errorMsg);
            }
        }
    }
    public static void assertResponseHasKeys(Map<String, Object> response, HashSet<String> expectedKeys) {
        HashSet<String> missingKeys = new HashSet<>();
        if(response.isEmpty()) {
            String errorMsg = "Response has no fields inside";
            logger.error(errorMsg);
            throw new AssertionError(errorMsg);
        }
        for (String key : expectedKeys) {
            if (!response.containsKey(key)) {
                missingKeys.add(key);
            }
        }
        if (!missingKeys.isEmpty()) {
            StringBuilder errorMsg = new StringBuilder("Response does not contain expected keys: ");
            for (String key : missingKeys) {
                errorMsg.append("'").append(key).append("', ");
            }
            errorMsg.setLength(errorMsg.length() - 2);
            logger.error(errorMsg.toString());
            throw new AssertionError(errorMsg.toString());
        }
    }
}
