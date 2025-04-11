package ru.example.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.userservice.dto.PurchaseDto;
import ru.example.userservice.dto.request.CreatePurchaseRequest;
import ru.example.userservice.service.PurchaseService;

import java.util.List;

/**
 * Контроллер для работы с покупками
 */
@RestController
@RequestMapping("/users/{userId}/purchases")
@RequiredArgsConstructor
@Tag(name = "Purchase Controller API", description = "Контроллер для управления покупками пользователя")
public class PurchaseController {

    private final PurchaseService purchaseService;

    /**
     * Создание покупки
     *
     * @param createPurchaseRequest Запрос на создание покупки
     * @param userId                ID пользователя, для которого создается покупка
     * @return {@link PurchaseDto DTO} созданной покупки
     */
    @PostMapping
    @Operation(
            summary = "Создать покупку",
            description = "Создает новую покупку для указанного пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Покупка успешно создана",
                    content = @Content(schema = @Schema(implementation = PurchaseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные входные данные для создания покупки"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пользователь не найден"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутрення ошибка сервера"
            )
    })
    public PurchaseDto create(
            @RequestBody @Schema(description = "Данные для создания покупки") CreatePurchaseRequest createPurchaseRequest,
            @PathVariable("userId") @Parameter(description = "ID пользователя") Long userId
    ) {
        return purchaseService.create(createPurchaseRequest, userId);
    }

    /**
     * Получение покупок пользователя
     *
     * @param userId     ID пользователя, покупки которого требуется получить
     * @param pageNumber Номер страницы
     * @param pageSize   Размер страницы
     * @return Список {@link PurchaseDto DTO} покупок
     */
    @GetMapping
    @Operation(
            summary = "Получить список покупок пользователя",
            description = "Возвращает список покупок для указанного пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список покупок пользователя успешно получен",
                    content = @Content(schema = @Schema(implementation = PurchaseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные входные параметры для получения покупок пользователя"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Пользователь не найден"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутрення ошибка сервера"
            )
    })
    public List<PurchaseDto> getByUser(
            @PathVariable("userId") @Parameter(description = "ID пользователя") Long userId,
            @RequestParam(value = "page", defaultValue = "0") @Parameter(description = "Номер страницы") int pageNumber,
            @RequestParam(value = "size", defaultValue = "10") @Parameter(description = "Кол-во элементов на странице") int pageSize
    ) {
        return purchaseService.getByUser(userId, pageNumber, pageSize);
    }
}
