package ru.clevertec.check.entity;

import ru.clevertec.check.builder.ProductBuilderInBasket;
import ru.clevertec.check.util.FileHandler;
import ru.clevertec.check.util.InputHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

public class ConcreteCheckWithDiscount extends Check {
    private InputHandler inputHandler;
    private FileHandler fileHandler;

    public ConcreteCheckWithDiscount(InputHandler inputHandler, FileHandler fileHandler) {
        this.inputHandler = inputHandler;
        this.fileHandler = fileHandler;
    }

    @Override
    public void createCheck() {
        String discount = inputHandler.getDiscount();
        String balance = inputHandler.getBalance();
        List<String> purchase = inputHandler.getPurchase();


        Map<Integer, DiscountCard> mapDiscount = fileHandler.getMapDiscount();
        Map<Long, Product> mapProduct = fileHandler.getMapProduct();
        DiscountCard discountCard = new DiscountCard();
        discountCard.setDiscountAmount((short) 2);
        if(mapDiscount.get(Integer.parseInt(discount)) != null) {
            discountCard = mapDiscount.get(Integer.parseInt(discount));
        }

        DebitCard debitCard = new DebitCard();
        debitCard.setBalanceDebitCard(BigDecimal.valueOf(Double.parseDouble(balance)));

        List<ProductInBascket> temp = new ArrayList<>();
        for (String product : purchase) {
            String[] split = product.split("");
            Product prod = mapProduct.get(Long.parseLong(split[0]));
            ProductInBascket bascket = ProductBuilderInBasket.builder()
                    .setId(prod.getProductId())
                    .setDescription(prod.getProductDescription())
                    .setPrice(prod.getProductPrice())
                    .setIsWholesale(prod.isWholesaleProduct())
                    .setQuantity(Integer.parseInt(split[2]))
                    .build();
            temp.add(bascket);
        }

        super.setLocalDateTime(LocalDateTime.now());
        super.setProducts(temp);
        super.setDiscountCard(discountCard);

        calculation();
    }

    public void calculation() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.ZERO;
        BigDecimal totalWithDiscount = BigDecimal.ZERO;

        List<ProductInBascket> products = getProducts();



        BigDecimal discountValue = BigDecimal.valueOf(super.getDiscountCard().getDiscountAmount()).divide(BigDecimal.valueOf(100));
        for (ProductInBascket product : products) {
            BigDecimal discountValueTemp = discountValue;
            if(product.isWholesaleProduct() && product.getQuantity() >= 5) {
                discountValueTemp = BigDecimal.valueOf(0.1);
            }
            BigDecimal itemCostWithoutDiscount = BigDecimal.valueOf(product.getQuantity()).multiply(product.getProductPrice());
            BigDecimal itemDiscount = itemCostWithoutDiscount.multiply(discountValueTemp);
            BigDecimal itemCost = itemCostWithoutDiscount.subtract(itemDiscount);

            product.setDiscount(itemDiscount.setScale(2, RoundingMode.HALF_UP));
            product.setTotal(itemCost.setScale(2, RoundingMode.HALF_UP));

            totalPrice = totalPrice.add(itemCostWithoutDiscount);
            totalDiscount = totalDiscount.add(itemDiscount);
            totalWithDiscount = totalWithDiscount.add(itemCost);
        }
        super.setTotalPrice(totalPrice.setScale(2, RoundingMode.HALF_UP));
        super.setTotalDiscount(totalDiscount.setScale(2, RoundingMode.HALF_UP));
        super.setTotalWithDiscount(totalWithDiscount.setScale(2, RoundingMode.HALF_UP));
    }
}
