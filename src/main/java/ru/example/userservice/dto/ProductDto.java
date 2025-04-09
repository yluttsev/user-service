package ru.example.userservice.dto;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        String title,
        BigDecimal price,
        Long category
) {
}
