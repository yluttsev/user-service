package ru.example.userservice.dto.request;

import java.math.BigDecimal;

public record CreateProductRequest(
        String name,
        Long category,
        BigDecimal price
) {
}
