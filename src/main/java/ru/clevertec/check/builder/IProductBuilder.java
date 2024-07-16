package ru.clevertec.check.builder;


import ru.clevertec.check.entity.Product;

import java.math.BigDecimal;

public interface IProductBuilder {
    public IProductBuilder builder();
    public IProductBuilder setId(Long productId);
    public IProductBuilder setDescription(String productDescription);
    public IProductBuilder setPrice(BigDecimal productPrice);
    public IProductBuilder setIsWholesale(boolean isWholesaleProduct);
    public IProductBuilder setQuantity(int quantity);
    public Product build();

}
