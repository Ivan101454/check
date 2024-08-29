package ru.clevertec.check.util;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public final class  PropertiesUtilTest {

    @Test
    void init() {
    }

    @Test
    void getProperties() {
    }

    @Test
    void loadProperties() {
        String password = PropertiesUtil.get("datasource.password");
        String username = PropertiesUtil.get("datasource.username");
        String url = PropertiesUtil.get("datasource.url");
        String properties = System.getProperty("asd");
        System.out.println(properties);
        assertThat(password).isEqualTo("postgres");
        assertThat(username).isEqualTo("postgres");
        assertThat(url).isEqualTo("jdbc:postgresql://localhost:5432/check");
    }
}