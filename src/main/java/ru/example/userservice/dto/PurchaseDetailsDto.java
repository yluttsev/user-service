package ru.example.userservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PurchaseDetailsDto {
    private PurchaseDto purchase;
    private List<ProductDto> products;
}
