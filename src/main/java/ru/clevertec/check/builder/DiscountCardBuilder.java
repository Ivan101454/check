package ru.clevertec.check.builder;

import ru.clevertec.check.entity.DiscountCard;

public class DiscountCardBuilder implements IDiscountBuilder{
    private Long idDiscountCard;
    private int numberOfDiscountCard;
    private short discountAmount;
    public static DiscountCardBuilder builder() {
        return new DiscountCardBuilder();
    }
    @Override
    public IDiscountBuilder setId(Long idDiscountCard) {
        this.idDiscountCard = idDiscountCard;
        return this;
    }

    @Override
    public IDiscountBuilder setNumberCard(int numberOfDiscountCard) {
        this.numberOfDiscountCard = numberOfDiscountCard;
        return this;
    }

    @Override
    public IDiscountBuilder setAmount(short discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    @Override
    public DiscountCard build() {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setIdDiscountCard(idDiscountCard);
        discountCard.setNumberOfDiscountCard(numberOfDiscountCard);
        discountCard.setDiscountAmount(discountAmount);
        return discountCard;
    }
}
