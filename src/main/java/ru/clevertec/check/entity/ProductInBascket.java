package ru.clevertec.check.entity;

public class ProductInBascket extends Product{
    private int quantityInBascket;
    @Override
    public int getQuantity() {
        return quantityInBascket;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantityInBascket = quantity;
    }
}
