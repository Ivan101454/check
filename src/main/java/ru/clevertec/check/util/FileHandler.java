package ru.clevertec.check.util;

import ru.clevertec.check.builder.DiscountCardBuilder;
import ru.clevertec.check.builder.ProductBuilderInStock;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.entity.Product;
import ru.clevertec.check.entity.ProductInStock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHandler {
    private InputHandler inputHandler;
    private static final FileHandler FILEHANDLER = new FileHandler();
    private String pathFileProduct;
    private String pathFileDiscount = "./src/main/resources/discountCards.csv";
    private List<String> stringsProducts = new ArrayList<>();
    private List<String> stringsDiscount = new ArrayList<>();
    private Map<Long, Product> mapProduct = new HashMap<>();
    private Map<Integer, DiscountCard> mapDiscount = new HashMap<>();

    public Map<Long, Product> getMapProduct() {
        return mapProduct;
    }

    public Map<Integer, DiscountCard> getMapDiscount() {
        return mapDiscount;
    }

    public static FileHandler getInstance() {
        return FILEHANDLER;
    }

    private FileHandler() {
    }

    public void handler() throws IOException {
        pathFileProduct = inputHandler.getPathToFile();
        try (BufferedReader products = new BufferedReader(new FileReader(pathFileProduct));
             BufferedReader discount = new BufferedReader(new FileReader(pathFileDiscount))) {
            String tempProduct;
            while ((tempProduct = products.readLine()) != null) {
                stringsProducts.add(tempProduct);
            }
            String tempDiscount;
            while ((tempDiscount=discount.readLine())!= null) {
                stringsDiscount.add(tempDiscount);
            }
            for (int i = 1; i < stringsProducts.size(); i++) {
                String[] split = stringsProducts.get(i).trim().split(";");
                ProductInStock product = ProductBuilderInStock.builder().
                        setId(Long.parseLong(split[0]))
                        .setDescription(split[1])
                        .setPrice(BigDecimal.valueOf(Double.parseDouble(split[2])))
                        .setQuantity(Integer.parseInt(split[3]))
                        .setIsWholesale(Boolean.parseBoolean(split[4]))
                        .build();
                mapProduct.put(product.getProductId(), product);
            }

            for (int i = 1; i < stringsDiscount.size(); i++) {
                String[] split = stringsDiscount.get(i).trim().split(";");
                DiscountCard discountCard = DiscountCardBuilder.builder()
                        .setId(Long.valueOf(split[0]))
                        .setNumberCard(Integer.parseInt(split[1]))
                        .setAmount(Short.parseShort(split[2]))
                        .build();
                mapDiscount.put(discountCard.getNumberOfDiscountCard(), discountCard);
            }
        }
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }
}
