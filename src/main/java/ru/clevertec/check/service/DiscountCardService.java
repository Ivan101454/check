package ru.clevertec.check.service;

import ru.clevertec.check.builder.DiscountCardBuilder;
import ru.clevertec.check.dao.CrudDiscountCard;
import ru.clevertec.check.dao.DiscountCardDao;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.http.dto.DiscountCardDto;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class DiscountCardService implements IDiscountCardService {
    private static final DiscountCardService INSTANCE = new DiscountCardService();
    private final DiscountCardDao crudDiscountCard = DiscountCardDao.getInstance();

    private DiscountCardService() {

    }

    public static DiscountCardService getInstance() {
        return INSTANCE;
    }

    @Override
    public void addDiscountCart(DiscountCardDto discountCardDto) {
        crudDiscountCard.save(new DiscountCardBuilder()
                .builder()
                        .setNumberCard(discountCardDto.getNumberOfDiscountCard())
                        .setAmount(discountCardDto.getDiscountAmount())
                .build());
    }

    @Override
    public Optional<DiscountCardDto> findByNumber(Integer number) {
        Optional<DiscountCard> byNumber = crudDiscountCard.findByNumber(number);
        return Optional.of(new DiscountCardDto(byNumber.get().getNumberOfDiscountCard(),
                byNumber.get().getDiscountAmount()));
    }
    public List<DiscountCardDto> findAll() {
        return  crudDiscountCard.allCard().stream()
                .map(card -> new DiscountCardDto(
                        card.getNumberOfDiscountCard(),
                        card.getDiscountAmount()
                )).collect(toList());
    }

}