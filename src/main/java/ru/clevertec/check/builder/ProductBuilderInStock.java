package ru.clevertec.check.builder;

import ru.clevertec.check.entity.ProductInStock;

import java.math.BigDecimal;

public class ProductBuilderInStock implements IProductBuilder{
    private Long productId;
    private String productDescription;
    private BigDecimal productPrice;
    private boolean isWholesaleProduct;
    private int quantity;
    public ProductBuilderInStock builder() {
        return new ProductBuilderInStock();
    }
    public ProductBuilderInStock setId(Long productId) {
        this.productId = productId;
        return this;
    }
    public ProductBuilderInStock setDescription(String productDescription) {
        this.productDescription = productDescription;
        return this;
    }
    public ProductBuilderInStock setPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
        return this;
    }
    public ProductBuilderInStock setIsWholesale(boolean isWholesaleProduct) {
        this.isWholesaleProduct = isWholesaleProduct;
        return this;
    }
    public ProductBuilderInStock setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
    public ProductInStock build() {
        ProductInStock productInStock = new ProductInStock();
        productInStock.setProductId(productId);
        productInStock.setProductDescription(productDescription);
        productInStock.setProductPrice(productPrice);
        productInStock.setWholesaleProduct(isWholesaleProduct);
        productInStock.setQuantity(quantity);
        return productInStock;
    }
}
