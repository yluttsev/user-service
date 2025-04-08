package ru.example.userservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PurchaseDto {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime purchaseDate;
    private Long userId;
}
