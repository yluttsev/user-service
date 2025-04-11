package ru.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO покупки
 *
 * @param id           ID покупки
 * @param amount       Общая сумма покупки
 * @param purchaseDate Дата покупки
 * @param products     Список {@link ProductDto DTO} продуктов
 */
public record PurchaseDto(
        Long id,
        BigDecimal amount,

        @JsonProperty("purchase_date")
        long purchaseDate,
        List<ProductDto> products
) {
}
