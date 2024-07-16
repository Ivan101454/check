package ru.clevertec.check.builder;

import ru.clevertec.check.entity.DiscountCard;

public interface IDiscountBuilder {
    public IDiscountBuilder builder();

    public IDiscountBuilder setId(Long idDiscountCard);
    public IDiscountBuilder setNumberCard(int numberOfDiscountCard);
    public IDiscountBuilder setAmount(short discountAmount);
    public DiscountCard build();
}
