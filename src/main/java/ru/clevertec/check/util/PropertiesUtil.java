package ru.clevertec.check.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public static final Properties PROPERTIES = new Properties();
    private PropertiesUtil(){
    }
    {
        loadProperties();
    }
    public static Properties init() {
        return PROPERTIES;
    }
    public static String getProperties(String key) {
        return PROPERTIES.getProperty(key);
    }
    public static void loadProperties() {
        try(var resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
