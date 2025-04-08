package ru.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record UserDto(
        Long id,
        Long category,
        String name,
        String email,

        @JsonProperty("total_spent")
        BigDecimal totalSpent
) {
}
