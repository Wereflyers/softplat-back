package ru.yandex.workshop.main.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.workshop.main.dto.user.response.AdminResponseDto;
import ru.yandex.workshop.main.message.LogMessage;
import ru.yandex.workshop.main.service.admin.AdminService;

import java.security.Principal;

@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "Получение информации об админе - просмотр своего ЛК", description = "Доступ для админа")
    @PreAuthorize("hasAuthority('admin:write')")
    @GetMapping
    public AdminResponseDto getAdminByEmail(Principal principal) {
        log.info(LogMessage.TRY_GET_ADMIN.label, principal.getName());
        return adminService.getAdminDto(principal.getName());
    }
}
