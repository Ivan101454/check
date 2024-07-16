package ru.clevertec.check.postgresCommand;

import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.entity.Product;

import java.util.List;

public interface IPostgresDml {
    public List<Product> getProductById(List<Long> idList);
    public DiscountCard getDiscountCardByNumber(int numberCard);
}
