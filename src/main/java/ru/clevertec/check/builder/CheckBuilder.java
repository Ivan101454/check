package ru.clevertec.check.builder;

import ru.clevertec.check.CheckRunner;
import ru.clevertec.check.entity.Check;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.entity.ProductInBascket;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CheckBuilder implements ICheckBuilder{
    private LocalDateTime localDateTime;
    private List<ProductInBascket> products;
    private DiscountCard discountCard;
    private BigDecimal totalPrice;
    private BigDecimal totalDiscount;
    private BigDecimal totalWithDiscount;
    public static CheckBuilder builder() {
        return new CheckBuilder();
    }
    @Override
    public CheckBuilder setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    @Override
    public CheckBuilder setProducts(List<ProductInBascket> products) {
        this.products = products;
        return this;
    }

    @Override
    public CheckBuilder setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
        return this;
    }

//    @Override
//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.t
//    }
//
//    @Override
//    public void setTotalDiscount(BigDecimal totalDiscount) {
//
//    }
//
//    @Override
//    public void setTotalWithDiscount(BigDecimal totalWithDiscount) {
//
//    }
//    public Check build() {
//        Check check = new Check();
//        check.setLocalDateTime(localDateTime);
//        check.setProducts(products);
//        check.setDiscountCard(discountCard);
//        return check;
//    }
}
