package ru.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * DTO пользователя
 *
 * @param id         ID пользователя
 * @param category   ID категории пользователя
 * @param name       Имя пользователя
 * @param email      Email пользователя
 * @param totalSpent Общая сумма покупок пользователя
 */
public record UserDto(
        Long id,
        Long category,
        String name,
        String email,

        @JsonProperty("total_spent")
        BigDecimal totalSpent
) {
}
