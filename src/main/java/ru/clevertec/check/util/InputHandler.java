package ru.clevertec.check.util;

import ru.clevertec.check.exception.CustomException;
import ru.clevertec.check.exception.TextErrorException;
import ru.clevertec.check.exception.WriteError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    private static final InputHandler INPUTHANDLER = new InputHandler();
    private List<String> list = new ArrayList<>();
    private String discount;
    private String balance;
    private String pathToFile;
    private String saveToFile;

    public void handler(String[] mas) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mas.length; i++) {
            stringBuilder.append(mas[i] + " ");
        }
        String str = stringBuilder.toString().trim();
        Pattern patternProduct = Pattern.compile("\\d-\\d");
        Matcher matcherProduct = patternProduct.matcher(str);
        Pattern patternDiscount = Pattern.compile("discountCard=\\d*");
        Matcher matcherDiscount = patternDiscount.matcher(str);
        Pattern patternBalance = Pattern.compile("balanceDebitCard=\\d*");
        Matcher matcherBalance = patternBalance.matcher(str);

        Pattern patternPathToFile = Pattern.compile("pathToFile=\\S+");
        Matcher matcherPathToFile = patternPathToFile.matcher(str);
        Pattern patternSaveToFile = Pattern.compile("saveToFile=\\S+");
        Matcher matcherSaveToFile = patternSaveToFile.matcher(str);

        while (matcherProduct.find()) {
            list.add(matcherProduct.group());
        }

        if (list.isEmpty()) {
            try {
                throw new CustomException(TextErrorException.BAD_REQUEST);
            } catch (CustomException e) {
                new WriteError(e).writeFile();
            }
        }
        if (matcherSaveToFile.find()) {
            String pathTemp = matcherSaveToFile.group();
            saveToFile = pathTemp.substring(11).trim();
            WriteError.setOutput(saveToFile);
        } else {
            try {
                throw new CustomException(TextErrorException.BAD_REQUEST);
            } catch (CustomException e) {
                new WriteError(e).writeFile();
            }
        }


        if (matcherPathToFile.find()) {
            String pathTemp = matcherPathToFile.group();
            pathToFile = pathTemp.substring(11).trim();
        } else {
            try {
                throw new CustomException(TextErrorException.BAD_REQUEST);
            } catch (CustomException e) {
                new WriteError(e).writeFile();
            }
        }


        if (matcherDiscount.find()) {
            String discountTemp = matcherDiscount.group();
            Pattern compileTemp = Pattern.compile("\\d+");
            Matcher matcherTemp = compileTemp.matcher(discountTemp);
            if (matcherTemp.find()) {
                discount = matcherTemp.group();
            }
        }

        if (matcherBalance.find()) {
            String balanceTemp = matcherBalance.group();
            Pattern compileTemp = Pattern.compile("\\d+");
            Matcher matcherTemp = compileTemp.matcher(balanceTemp);
            while (matcherTemp.find()) {
                balance = matcherTemp.group();
            }
        }
    }

    private InputHandler() {

    }

    public static InputHandler getInstance() {
        return INPUTHANDLER;
    }

    public List<String> getPurchase() {
        return list;
    }

    public String getDiscount() {
        return discount;
    }

    public String getBalance() {
        return balance;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public String getSaveToFile() {
        return saveToFile;
    }
}
