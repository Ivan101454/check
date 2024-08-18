package ru.clevertec.check.http.dto;

import java.util.Objects;

public class DiscountCardDto {
    private int numberOfDiscountCard;
    private short discountAmount;

    public DiscountCardDto(int numberOfDiscountCard, short discountAmount) {
        this.numberOfDiscountCard = numberOfDiscountCard;
        this.discountAmount = discountAmount;
    }

    public int getNumberOfDiscountCard() {
        return numberOfDiscountCard;
    }

    public void setNumberOfDiscountCard(int numberOfDiscountCard) {
        this.numberOfDiscountCard = numberOfDiscountCard;
    }

    public short getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(short discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCardDto that = (DiscountCardDto) o;
        return numberOfDiscountCard == that.numberOfDiscountCard && discountAmount == that.discountAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfDiscountCard, discountAmount);
    }

    @Override
    public String toString() {
        return "DiscountCardDto{" +
               ", numberOfDiscountCard=" + numberOfDiscountCard +
               ", discountAmount=" + discountAmount +
               '}';
    }
}
