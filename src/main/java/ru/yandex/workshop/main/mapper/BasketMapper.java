package ru.yandex.workshop.main.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.yandex.workshop.main.dto.basket.BasketDto;
import ru.yandex.workshop.main.model.buyer.Basket;

@Mapper(componentModel = "spring", uses = BasketPositionMapper.class)
@Component
public interface BasketMapper {
    BasketDto basketToBasketDto(Basket basket);
}