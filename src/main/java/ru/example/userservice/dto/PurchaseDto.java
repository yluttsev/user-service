package ru.example.userservice.dto;

import lombok.*;
import ru.example.userservice.entity.PurchaseItems;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    private List<PurchaseItems> items;
}
