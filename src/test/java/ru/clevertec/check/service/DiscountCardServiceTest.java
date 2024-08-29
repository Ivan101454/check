package ru.clevertec.check.service;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.http.dto.DiscountCardDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCardServiceTest {

    @Test
    void findAll() {
        DiscountCardService instance = DiscountCardService.getInstance();
        List<DiscountCardDto> all = instance.findAll();
        all.forEach(System.out::println);
    }
}