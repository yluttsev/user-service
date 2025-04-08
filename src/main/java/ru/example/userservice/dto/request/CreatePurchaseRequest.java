package ru.example.userservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.example.userservice.dto.ProductDto;
import ru.example.userservice.dto.UserDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record CreatePurchaseRequest(
        BigDecimal amount,

        @JsonProperty("purchase_date")
        Instant purchaseDate,
        List<ProductDto> products,
        UserDto user
) {
}
