package io.framework.Utils;

import io.framework.Constants.FrameworkConstants;
import io.framework.Enums.PropertiesType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtils {
    private PropertyUtils() {}

    private static Properties properties = new Properties();
    private static Map<String, String> MAP = new HashMap<>();

    static {
        try(FileInputStream inputStream = new FileInputStream(FrameworkConstants.getPropertyFilePath())) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0); // closes execution way before code starts running
        }
        properties.entrySet().forEach(e -> MAP.put(String.valueOf(e.getKey()), String.valueOf(e.getValue())));

    }
    public static String getValue(PropertiesType key) {
        return MAP.get(key.name().toLowerCase());
    }
}
