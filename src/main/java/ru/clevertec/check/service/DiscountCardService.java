package ru.clevertec.check.service;

import ru.clevertec.check.dao.CrudDiscountCard;

import java.util.Optional;

public class DiscountCardService implements IDiscountCardService {
    CrudDiscountCard crudDiscountCard;

    public DiscountCardService(CrudDiscountCard crudDiscountCard) {
        this.crudDiscountCard = crudDiscountCard;
    }

    @Override
    public Optional findByNumber(Integer number) {
        return crudDiscountCard.findByNumber(number);
    }
}