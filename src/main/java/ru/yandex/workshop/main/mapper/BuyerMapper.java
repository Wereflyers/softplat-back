package ru.yandex.workshop.main.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.yandex.workshop.main.dto.user.BuyerUpdateDto;
import ru.yandex.workshop.main.dto.user.response.BuyerResponseDto;
import ru.yandex.workshop.main.dto.user.response.BuyersListResponseDto;
import ru.yandex.workshop.main.model.buyer.Buyer;

import java.util.List;

@Mapper
@Component
public interface BuyerMapper {
    BuyerResponseDto buyerToBuyerResponseDto(Buyer buyer);

    Buyer buyerDtoToBuyer(BuyerUpdateDto buyerUpdateDto);

    default BuyersListResponseDto toBuyersListResponseDto(List<BuyerResponseDto> buyers) {
        return BuyersListResponseDto.builder().buyers(buyers).build();
    }
}
