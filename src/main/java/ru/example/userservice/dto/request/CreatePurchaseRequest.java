package ru.example.userservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Запрос на создание покупки
 *
 * @param productIds Список ID продуктов
 */
public record CreatePurchaseRequest(

        @JsonProperty("products")
        List<Long> productIds
) {
}
