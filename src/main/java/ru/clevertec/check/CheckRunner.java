package ru.clevertec.check;

import ru.clevertec.check.entity.*;
import ru.clevertec.check.exception.CustomException;
import ru.clevertec.check.exception.WriteError;
import ru.clevertec.check.postgresCommand.IPostgresDml;
import ru.clevertec.check.postgresCommand.PostgresDml;
import ru.clevertec.check.postgresCommand.PostgresOperation;
import ru.clevertec.check.util.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        args = "3-1 2-1 2-1 5-1 2-3 discountCard=1111 balanceDebitCard=100 saveToFile=result.csv datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres datasource.password=postgres".split(" ");

        InputHandler inputHandler = InputHandler.getInstance();
        FileHandler fileHandler = FileHandler.getInstance();
        inputHandler.handler(args);
        PropertiesUtil.loadProperties();
        fileHandler.handler();

        checkData(inputHandler);

//        var operation = PostgresOperation.createPostgresOperation();




//        Check check;
//        if (inputHandler.getDiscount() != null) {
//            check = new ConcreteCheckWithDiscount(inputHandler, fileHandler);
//        } else check = new ConcreteCheckWithoutDiscount(inputHandler, fileHandler);
//        check.order();
//        CheckPresentation checkPresentation = new CheckPresentation(check);
//        checkPresentation.writeToFile(inputHandler.getSaveToFile());
//        System.out.println(checkPresentation.toString());
    }

    private static void checkData(InputHandler inputHandler) {
        IPostgresDml iPostgresDml = new PostgresDml();
        int numberEnterDiscout = Integer.parseInt(inputHandler.getDiscount());
        DiscountCard discountCardByNumber = iPostgresDml.getDiscountCardByNumber(numberEnterDiscout);

        List<Product> productById = iPostgresDml.getProductById(inputHandler.getProductId());
        System.out.println(discountCardByNumber.toString());
        productById.forEach(System.out::println);
    }
}
