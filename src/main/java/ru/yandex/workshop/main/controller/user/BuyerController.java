package ru.yandex.workshop.main.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.workshop.main.dto.user.BuyerDto;
import ru.yandex.workshop.main.dto.user.response.BuyerResponseDto;
import ru.yandex.workshop.main.dto.user.response.FavoriteDto;
import ru.yandex.workshop.main.message.LogMessage;
import ru.yandex.workshop.main.service.buyer.BuyerFavoriteService;
import ru.yandex.workshop.main.service.buyer.BuyerService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/buyer")
public class BuyerController {
    private final BuyerService buyerService;
    private final BuyerFavoriteService favoriteService;

    @GetMapping("/{userId}")
    public BuyerResponseDto getBuyer(@PathVariable Long userId) {
        log.info(LogMessage.TRY_GET_BUYER.label, userId);
        return buyerService.getBuyer(userId);
    }

    @PreAuthorize("hasAuthority('buyer:write')")
    @PatchMapping
    public BuyerResponseDto updateBuyer(Principal principal, @RequestBody @Valid BuyerDto buyerDto) {
        log.info(LogMessage.TRY_PATCH_BUYER.label, principal.getName());
        return buyerService.updateBuyer(principal.getName(), buyerDto);
    }

    @PreAuthorize("hasAuthority('buyer:write')")
    @PostMapping("/{buyerEmail}/favorites/{productId}")
    public FavoriteDto createFavorite(Principal principal,
                                      @PathVariable Long productId) {
        log.info(LogMessage.TRY_BUYER_ADD_FAVORITE.label, "{}, {}", principal.getName(), productId);
        return favoriteService.create(principal.getName(), productId);
    }

    @PreAuthorize("hasAuthority('buyer:write')")
    @DeleteMapping("/{buyerEmail}/favorites/{productId}")
    public void deleteFavorite(Principal principal,
                               @PathVariable Long productId) {
        log.info(LogMessage.TRY_BUYER_DELETE_FAVORITE.label, "{}, {}", principal.getName(), productId);
        favoriteService.delete(principal.getName(), productId);
    }

    @PreAuthorize("hasAuthority('buyer:write')")
    @GetMapping("/{buyerEmail}/favorites")
    public List<FavoriteDto> getAll(Principal principal) {
        log.info(LogMessage.TRY_BUYER_GET_FAVORITE.label, principal.getName());
        List<FavoriteDto> response = favoriteService.getAll(principal.getName());
        log.info("{}", response);
        return response;
    }
}
