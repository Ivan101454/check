package ru.clevertec.check.service;

import ru.clevertec.check.entity.DiscountCard;

import java.util.Optional;

public interface IDiscountCardService {
    public Optional<DiscountCard> findByNumber(Integer number);
}
