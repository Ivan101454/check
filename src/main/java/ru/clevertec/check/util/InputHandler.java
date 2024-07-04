package ru.clevertec.check.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    private static final InputHandler INPUTHANDLER = new InputHandler();
    private List<String> list = new ArrayList<>();
    private String discount;
    private String balance;
    public void handler(String[] mas) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mas.length; i++) {
            stringBuilder.append(mas[i] + " ");
        }
        String str = stringBuilder.toString().trim();
        Pattern patternProduct = Pattern.compile("\\d-\\d");
        Matcher matcherProduct = patternProduct.matcher(str);
        Pattern patternDiscount = Pattern.compile("discountCard=\\d{4}");
        Matcher matcherDiscount = patternDiscount.matcher(str);
        Pattern patternBalance = Pattern.compile("balanceDebitCard=\\d{3}");
        Matcher matcherBalance = patternBalance.matcher(str);
        while (matcherProduct.find()) {
            list.add(matcherProduct.group());
        }
        if(matcherDiscount.find()) {
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

    public List<String> getPurchase () {
        return list;
    }

    public String getDiscount() {
        return discount;
    }

    public String getBalance() {
        return balance;
    }
}
