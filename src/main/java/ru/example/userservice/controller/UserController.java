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
import org.springframework.web.bind.annotation.RestController;
import ru.example.userservice.dto.UserDto;
import ru.example.userservice.dto.request.CreateUserRequest;
import ru.example.userservice.service.UserService;

/**
 * Контроллер для работы с пользователями
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller API", description = "Контроллер для управления пользователями")
public class UserController {

    private final UserService userService;

    /**
     * Создание нового пользователя
     *
     * @param createUserRequest {@link CreateUserRequest Запрос} на создание пользователя
     * @return {@link UserDto DTO} созданного пользователя
     */
    @PostMapping
    @Operation(
            summary = "Создать нового пользователя",
            description = "Создает нового пользователя в системе"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно создан",
                    content = @Content(schema = @Schema(implementation = UserDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидные данные для создания пользователя"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера"
            )
    })
    public UserDto create(
            @RequestBody @Schema(description = "Данные для создания пользователя") CreateUserRequest createUserRequest
    ) {
        return userService.create(createUserRequest);
    }

    /**
     * Получение пользователя
     *
     * @param id ID пользователя, которого необходимо получить
     * @return {@link UserDto DTO} пользователя
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Получить пользователя по ID",
            description = "Возвращает данные пользователя по его ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь успешно найден",
                    content = @Content(schema = @Schema(implementation = UserDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невалидный ID пользователя"
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
    public UserDto getById(
            @PathVariable @Parameter(description = "ID пользователя") Long id
    ) {
        return userService.getDtoById(id);
    }
}
