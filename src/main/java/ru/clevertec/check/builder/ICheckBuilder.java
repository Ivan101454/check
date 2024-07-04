package ru.clevertec.check.builder;

import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.entity.ProductInBascket;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ICheckBuilder {
    public CheckBuilder setLocalDateTime(LocalDateTime localDateTime);
    public CheckBuilder setProducts(List<ProductInBascket> products);
    public CheckBuilder setDiscountCard(DiscountCard discountCard);
//    public void setTotalPrice(BigDecimal totalPrice);
//    public void setTotalDiscount(BigDecimal totalDiscount);
//    public void setTotalWithDiscount(BigDecimal totalWithDiscount);
//

}
