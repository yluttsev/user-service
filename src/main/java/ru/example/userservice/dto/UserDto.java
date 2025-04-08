package ru.example.userservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private BigDecimal totalSpent;
}
