package ru.clevertec.check;

import ru.clevertec.check.entity.Check;
import ru.clevertec.check.entity.ConcreteCheckWithDiscount;
import ru.clevertec.check.entity.ConcreteCheckWithoutDiscount;
import ru.clevertec.check.exception.CustomException;
import ru.clevertec.check.exception.WriteError;
import ru.clevertec.check.util.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        args = "3-1 2-1 2-1 5-1 2-3 discountCard=1111 balanceDebitCard=100 saveToFile=result.csv datasource.url=jdbc:postgresql://localhost:5432/Test datasource.username=postgres datasource.password=postgres".split(" ");

        InputHandler inputHandler = InputHandler.getInstance();
        FileHandler fileHandler = FileHandler.getInstance();
        inputHandler.handler(args);
        PropertiesUtil.loadProperties();
        fileHandler.handler();

        String sql = """
                DROP DATABASE game;
                """;

        try(var connection = JdbcConnectionManager.open();
            var statement = connection.createStatement()) {

            int transactionIsolation = connection.getTransactionIsolation();
            System.out.println(transactionIsolation);
            var executeResult = statement.execute(sql);
            System.out.println(executeResult);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//        Check check;
//        if (inputHandler.getDiscount() != null) {
//            check = new ConcreteCheckWithDiscount(inputHandler, fileHandler);
//        } else check = new ConcreteCheckWithoutDiscount(inputHandler, fileHandler);
//        check.order();
//        CheckPresentation checkPresentation = new CheckPresentation(check);
//        checkPresentation.writeToFile(inputHandler.getSaveToFile());
//        System.out.println(checkPresentation.toString());
    }
}
