package ru.clevertec.check.entity;

import java.math.BigDecimal;

public abstract class Product {
    private Long productId;
    private String productDescription;
    private BigDecimal productPrice;
    private boolean isWholesaleProduct;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public abstract int getQuantity();

    public abstract void setQuantity(int quantity);

    public boolean isWholesaleProduct() {
        return isWholesaleProduct;
    }

    public void setWholesaleProduct(boolean wholesaleProduct) {
        isWholesaleProduct = wholesaleProduct;
    }


}
