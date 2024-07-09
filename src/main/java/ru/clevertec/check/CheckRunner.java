package ru.clevertec.check;

import ru.clevertec.check.entity.Check;
import ru.clevertec.check.entity.ConcreteCheckWithDiscount;
import ru.clevertec.check.entity.ConcreteCheckWithoutDiscount;
import ru.clevertec.check.util.CheckPresentation;
import ru.clevertec.check.util.FileHandler;
import ru.clevertec.check.util.InputHandler;

import java.io.IOException;

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        InputHandler inputHandler = InputHandler.getInstance();
        FileHandler fileHandler = FileHandler.getInstance();
        fileHandler.setInputHandler(inputHandler);
        inputHandler.handler(args);
        fileHandler.handler();
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
