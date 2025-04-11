package ru.example.userservice.dto.request;

/**
 * Запрос на создание пользователя
 *
 * @param name     Имя пользователя
 * @param email    Email пользователя
 * @param category ID категории пользователя
 */
public record CreateUserRequest(
        String name,
        String email,
        Long category
) {
}
