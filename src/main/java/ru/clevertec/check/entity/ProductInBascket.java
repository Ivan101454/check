package ru.clevertec.check.entity;

import java.math.BigDecimal;

public class ProductInBascket extends Product{
    private int quantityInBascket;
    private BigDecimal discount;
    private BigDecimal total;
    @Override
    public int getQuantity() {
        return quantityInBascket;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantityInBascket = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return super.toString() + "ProductInBascket{" +
                "quantityInBascket=" + quantityInBascket +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}
