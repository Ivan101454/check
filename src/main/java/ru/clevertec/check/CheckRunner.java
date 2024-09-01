package ru.clevertec.check;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.clevertec.check.dao.CrudDiscountCard;
import ru.clevertec.check.dao.CrudProductInStock;
import ru.clevertec.check.dao.DiscountCardDao;
import ru.clevertec.check.dao.ProductInStockDao;
import ru.clevertec.check.entity.*;
import ru.clevertec.check.http.dto.DiscountCardDto;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.util.*;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Scanner;

public class CheckRunner {

    public static void main(String[] args) throws IOException {
        var context = new ClassPathXmlApplicationContext("application.xml");
        DiscountCardService discount = context.getBean("disc", DiscountCardService.class);
        System.out.println(discount.findByNumber(1111).toString());


//        args = "3-1 2-1 2-1 5-1 2-3 discountCard=1111 balanceDebitCard=100 saveToFile=result.csv datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres datasource.password=postgres".split(" ");
//
//        InputHandler inputHandler = InputHandler.getInstance();
//        inputHandler.handler(args);
//        CrudProductInStock crudProductInStock = ProductInStockDao.getProductDao();
//        CrudDiscountCard crudDiscountCard = DiscountCardDao.getInstance();

//        checkData(inputHandler);


//        Check check;
//        if (inputHandler.getDiscount() != null) {
//            check = new ConcreteCheckWithDiscount(inputHandler, crudDiscountCard, crudProductInStock);
//        } else check = new ConcreteCheckWithoutDiscount(inputHandler, crudProductInStock);
//        check.order();
//        CheckPresentation checkPresentation = new CheckPresentation(check);
//        checkPresentation.writeToFile(inputHandler.getSaveToFile());
//        System.out.println(checkPresentation.toString());


    }
}

//    private static void checkData(InputHandler inputHandler) {
//        IPostgresDml iPostgresDml = new PostgresDml();
//        int numberEnterDiscout = Integer.parseInt(inputHandler.getDiscount());
//        DiscountCard discountCardByNumber = iPostgresDml.getDiscountCardByNumber(numberEnterDiscout);
//
//        List<Product> productById = iPostgresDml.getProductById(inputHandler.getProductId());
//        System.out.println(discountCardByNumber.toString());
//        productById.forEach(System.out::println);
//    }
