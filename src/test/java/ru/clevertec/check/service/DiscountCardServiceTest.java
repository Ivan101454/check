package ru.clevertec.check.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.builder.DiscountCardBuilder;
import ru.clevertec.check.dao.DiscountCardDao;
import ru.clevertec.check.entity.DiscountCard;
import ru.clevertec.check.service.paramresolver.ServiceParamResolver;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.DisplayName.class)
@ExtendWith({
        ServiceParamResolver.class,
        MockitoExtension.class
})
class DiscountCardServiceTest {
    @InjectMocks
    private DiscountCardService discountCardService;
    @Mock
    private DiscountCardDao discountCardDao;

    public DiscountCardServiceTest() {

    }

    @BeforeEach
    void prepare() {
//        this.discountCardDao = Mockito.mock(DiscountCardDao.class);
//        this.discountCardService = new DiscountCardService(discountCardDao);
    }

    @Test
    void shouldFindCardByNumber() {
        DiscountCard cardExpect = new DiscountCardBuilder().builder()
                .setId(1L)
                .setNumberCard(1111)
                .setAmount((short) 3)
                .build();
        Mockito.doReturn(Optional.of(cardExpect)).when(discountCardDao).findByNumber(1111);
        var card = discountCardService.findByNumber(1111);
        assertThat(card).isEqualTo(Optional.of(cardExpect));
    }
}