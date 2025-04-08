package ru.example.userservice.dto;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        String name,
        Long category,
        BigDecimal price
) {
}
