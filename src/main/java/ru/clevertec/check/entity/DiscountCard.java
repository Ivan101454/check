package ru.clevertec.check.entity;

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
}
