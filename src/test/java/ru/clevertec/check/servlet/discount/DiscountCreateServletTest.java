package ru.clevertec.check.servlet.discount;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.http.dto.DiscountCardDto;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.IDiscountCardService;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCreateServletTest {

    @Test
    void doPost() {
        IDiscountCardService discountCardService = DiscountCardService.getInstance();
        boolean b = discountCardService.addDiscountCart(
                DiscountCardDto.builder()
                        .numberOfDiscountCard(1111)
                        .discountAmount((short) 2)
                        .build()
        );

    }
}