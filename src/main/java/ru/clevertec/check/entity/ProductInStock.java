package ru.clevertec.check.entity;



public class ProductInStock extends Product {
    private int quantityInStock;

    @Override
    public int getQuantity() {
        return quantityInStock;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantityInStock = quantity;
    }


}
