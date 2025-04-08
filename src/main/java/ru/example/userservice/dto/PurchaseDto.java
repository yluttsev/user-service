package ru.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record PurchaseDto(
        Long id,
        BigDecimal amount,

        @JsonProperty("purchase_date")
        Instant purchaseDate,

        @JsonProperty("user_id")
        Long userId,
        List<ProductDto> products
) {
}
