package ru.clevertec.check.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Check {
    private LocalDateTime localDateTime;

    private DiscountCard discountCard;
    private BigDecimal totalPrice;
    private BigDecimal totalDiscount;
    private BigDecimal totalWithDiscount;

}
