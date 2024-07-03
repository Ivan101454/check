package ru.clevertec.check.builder;

import ru.clevertec.check.entity.ProductInBascket;

import java.math.BigDecimal;

public class ProductBuilderInBasket implements IProductBuilder{
    private Long productId;
    private String productDescription;
    private BigDecimal productPrice;
    private boolean isWholesaleProduct;
    private int quantity;
    public static ProductBuilderInBasket builder() {
        return new ProductBuilderInBasket();
    }
    public ProductBuilderInBasket setId(Long productId) {
        this.productId = productId;
        return this;
    }
    public ProductBuilderInBasket setDescription(String productDescription) {
        this.productDescription = productDescription;
        return this;
    }
    public ProductBuilderInBasket setPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
        return this;
    }
    public ProductBuilderInBasket setIsWholesale(boolean isWholesaleProduct) {
        this.isWholesaleProduct = isWholesaleProduct;
        return this;
    }
    public ProductBuilderInBasket setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
    public ProductInBascket build() {
        ProductInBascket productInBascket = new ProductInBascket();
        productInBascket.setProductId(productId);
        productInBascket.setProductDescription(productDescription);
        productInBascket.setProductPrice(productPrice);
        productInBascket.setWholesaleProduct(isWholesaleProduct);
        productInBascket.setQuantity(quantity);
        return productInBascket;
    }
}
