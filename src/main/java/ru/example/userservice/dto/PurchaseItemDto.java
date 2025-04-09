package ru.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PurchaseItemDto(
        Long id,

        @JsonProperty("product_id")
        Long productId
) {
}
