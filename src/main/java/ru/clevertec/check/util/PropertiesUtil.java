package ru.clevertec.check.util;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    @Getter
    private static Properties properties;
    @Getter
    private static final String INIT = "START";


//    static {
//        loadProperties();
//    }
    private PropertiesUtil() {

    }

    private PropertiesUtil(Properties properties) {
        PropertiesUtil.properties = properties;
        loadProperties();
    }


    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}