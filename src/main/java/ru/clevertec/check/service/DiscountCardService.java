package ru.clevertec.check.service;

import ru.clevertec.check.builder.DiscountCardBuilder;
import ru.clevertec.check.dao.CrudDiscountCard;
import ru.clevertec.check.dao.DiscountCardDao;
import ru.clevertec.check.http.dto.DiscountCardDto;

import java.util.Optional;

public class DiscountCardService implements IDiscountCardService {
    private static final DiscountCardService INSTANCE = new DiscountCardService();
    private final CrudDiscountCard crudDiscountCard = DiscountCardDao.getInstance();

    private DiscountCardService() {

    }

    public DiscountCardService getInstance() {
        return INSTANCE;
    }

    Optional findByNumber(Integer number) {

        return crudDiscountCard.findByNumber(number);
    }

    @Override
    public void addDiscountCart(DiscountCardDto discountCardDto) {
        crudDiscountCard.save(new DiscountCardBuilder()
                .builder()
                        .setNumberCard(discountCardDto.getNumberOfDiscountCard())
                        .setAmount((short) 2)
                .build());
    }

    @Override
    public Optional<DiscountCardDto> findByNumber() {
        return Optional.empty();
    }
}