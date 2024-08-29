package ru.clevertec.check.util;

import static org.junit.jupiter.api.Assertions.*;

class JdbcConnectionManagerTest {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}