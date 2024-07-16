package ru.clevertec.check.entity;

import ru.clevertec.check.builder.IProductBuilder;
import ru.clevertec.check.builder.ProductBuilderInBasket;
import ru.clevertec.check.exception.CustomException;
import ru.clevertec.check.exception.TextErrorException;
import ru.clevertec.check.exception.WriteError;
import ru.clevertec.check.util.FileHandler;
import ru.clevertec.check.util.InputHandler;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

public class ConcreteCheckWithDiscount extends Check {
    private InputHandler inputHandler;
    private FileHandler fileHandler;
    private DebitCard debitCard;
    private IProductBuilder iProductBuilder = new ProductBuilderInBasket();

    public ConcreteCheckWithDiscount(InputHandler inputHandler, FileHandler fileHandler) {
        this.inputHandler = inputHandler;
        this.fileHandler = fileHandler;
    }

    @Override
    public void createCheck() throws IOException {
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

        debitCard = new DebitCard();
        debitCard.setBalanceDebitCard(BigDecimal.valueOf(Double.parseDouble(balance)));

        List<ProductInBascket> temp = new ArrayList<>();
        for (String product : purchase) {
            String[] split = product.split("");
            Product prod = mapProduct.get(Long.parseLong(split[0]));
            if(prod == null) {
                try {
                    throw new CustomException(TextErrorException.BAD_REQUEST);
                } catch (CustomException e) {
                    new WriteError(e).writeFile();
                }
            }
            ProductInBascket bascket = (ProductInBascket) iProductBuilder.builder()
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

        try {
            calculation();
        } catch (CustomException e) {
           new WriteError(e).writeFile();
        }
    }

    public void calculation() throws CustomException {
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.ZERO;
        BigDecimal totalWithDiscount = BigDecimal.ZERO;

        List<ProductInBascket> products = getProducts();



        BigDecimal discountValue = BigDecimal.valueOf(super.getDiscountCard().getDiscountAmount()).divide(BigDecimal.valueOf(100));
        HashMap<Long, Product> productMap = new HashMap<>();
        for (ProductInBascket product : products) {
            if(productMap.containsKey(product.getProductId())) {
                int quantityOld = productMap.get(product.getProductId()).getQuantity();
                int quantityNew = product.getQuantity();
                int sum = quantityNew + quantityOld;
                productMap.put(product.getProductId(), iProductBuilder.builder().setQuantity(sum).build());
            } else
                productMap.put(product.getProductId(), product);
        }

        for (ProductInBascket product : products) {
            BigDecimal discountValueTemp = discountValue;
            if(product.isWholesaleProduct() && productMap.get(product.getProductId()).getQuantity() >= 5) {
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
        if (debitCard.getBalanceDebitCard().subtract(totalWithDiscount).compareTo(BigDecimal.ZERO) < 0) {
            throw new CustomException(TextErrorException.NOT_ENOUGH_MONEY);
        }
        super.setTotalPrice(totalPrice.setScale(2, RoundingMode.HALF_UP));
        super.setTotalDiscount(totalDiscount.setScale(2, RoundingMode.HALF_UP));
        super.setTotalWithDiscount(totalWithDiscount.setScale(2, RoundingMode.HALF_UP));

    }
}
