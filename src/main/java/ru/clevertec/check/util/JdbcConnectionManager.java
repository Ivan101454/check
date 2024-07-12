package ru.clevertec.check.util;

import ru.clevertec.check.exception.CustomException;
import ru.clevertec.check.exception.TextErrorException;
import ru.clevertec.check.exception.WriteError;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class JdbcConnectionManager {
    private static final String PASSWORD_KEY = "datasource.password";
    private static final String USERNAME_KEY = "datasource.username";
    private static final String URL_KEY = "datasource.url";
    private JdbcConnectionManager() {
    }
    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.getProperties(URL_KEY),
                    PropertiesUtil.getProperties(USERNAME_KEY),
                    PropertiesUtil.getProperties(PASSWORD_KEY));
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

}
