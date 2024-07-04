package ru.clevertec.check;

import ru.clevertec.check.entity.Check;
import ru.clevertec.check.entity.ConcreteCheckWithDiscount;
import ru.clevertec.check.util.CheckPresentation;
import ru.clevertec.check.util.FileHandler;
import ru.clevertec.check.util.InputHandler;

import java.io.IOException;

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        args = "3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100".split(" ");
        InputHandler inputHandler = InputHandler.getInstance();
        FileHandler fileHandler = FileHandler.getInstance();
        inputHandler.handler(args);
        fileHandler.handler();
        Check check = new ConcreteCheckWithDiscount(inputHandler, fileHandler);
        check.order();
        System.out.println(check.toString());
        CheckPresentation checkPresentation = new CheckPresentation(check);

    }
}
