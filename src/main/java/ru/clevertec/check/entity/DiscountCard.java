package ru.clevertec.check.entity;

import java.util.Objects;

public class DiscountCard {
    private Long idDiscountCard;
    private int numberOfDiscountCard;
    private short discountAmount;


    public Long getIdDiscountCard() {
        return idDiscountCard;
    }

    public void setIdDiscountCard(Long idDiscountCard) {
        this.idDiscountCard = idDiscountCard;
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
    public String toString() {
        return "DiscountCard{" +
                "idDiscountCard=" + idDiscountCard +
                ", numberOfDiscountCard=" + numberOfDiscountCard +
                ", discountAmount=" + discountAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return numberOfDiscountCard == that.numberOfDiscountCard && discountAmount == that.discountAmount && Objects.equals(idDiscountCard, that.idDiscountCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDiscountCard, numberOfDiscountCard, discountAmount);
    }
}
