package ru.example.userservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CreatePurchaseRequest(

        @JsonProperty("products")
        List<Long> productIds
) {
}
