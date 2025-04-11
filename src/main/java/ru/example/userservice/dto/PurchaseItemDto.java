package ru.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO продукта в покупке
 *
 * @param id        ID продукта в покупке
 * @param productId ID продукта из Recommendation Service
 */
public record PurchaseItemDto(
        Long id,

        @JsonProperty("product_id")
        Long productId
) {
}
