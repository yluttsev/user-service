package ru.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record PurchaseDto(
        Long id,
        BigDecimal amount,

        @JsonProperty("purchase_date")
        long purchaseDate,
        List<ProductDto> products
) {
}
