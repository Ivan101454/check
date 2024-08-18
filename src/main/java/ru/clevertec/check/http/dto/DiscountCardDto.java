package ru.clevertec.check.http.dto;

import java.util.Objects;

public class DiscountCardDto {
    private Long idDiscountCard;
    private int numberOfDiscountCard;

    public DiscountCardDto(Long idDiscountCard, int numberOfDiscountCard) {
        this.idDiscountCard = idDiscountCard;
        this.numberOfDiscountCard = numberOfDiscountCard;
    }

    public Long getIdDiscountCard() {
        return idDiscountCard;
    }

    public int getNumberOfDiscountCard() {
        return numberOfDiscountCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCardDto that = (DiscountCardDto) o;
        return numberOfDiscountCard == that.numberOfDiscountCard && Objects.equals(idDiscountCard, that.idDiscountCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDiscountCard, numberOfDiscountCard);
    }

    @Override
    public String toString() {
        return "DiscountCardDto{" +
               "idDiscountCard=" + idDiscountCard +
               ", numberOfDiscountCard=" + numberOfDiscountCard +
               '}';
    }
}
