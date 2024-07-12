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

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        args = "3-1 2-1 2-1 5-1 2-3 discountCard=1111 balanceDebitCard=100 saveToFile=result.csv datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres datasource.password=postgres".split(" ");

        InputHandler inputHandler = InputHandler.getInstance();
        FileHandler fileHandler = FileHandler.getInstance();
        inputHandler.handler(args);
        PropertiesUtil.loadProperties();
        fileHandler.handler();

        try(var connection = JdbcConnectionManager.open()) {
            int transactionIsolation = connection.getTransactionIsolation();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        Check check;
        if (inputHandler.getDiscount() != null) {
            check = new ConcreteCheckWithDiscount(inputHandler, fileHandler);
        } else check = new ConcreteCheckWithoutDiscount(inputHandler, fileHandler);
        check.order();
        CheckPresentation checkPresentation = new CheckPresentation(check);
        checkPresentation.writeToFile(inputHandler.getSaveToFile());
        System.out.println(checkPresentation.toString());
    }
}
