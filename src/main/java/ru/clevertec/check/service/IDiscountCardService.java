package ru.clevertec.check.service;

import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.http.dto.DiscountCardDto;

import java.util.List;
import java.util.Optional;

public interface IDiscountCardService {
    public void addDiscountCart(DiscountCardDto discountCardDto);
    public Optional<DiscountCardDto> findByNumber(Integer number);
    public List<DiscountCardDto> findAll();
}
