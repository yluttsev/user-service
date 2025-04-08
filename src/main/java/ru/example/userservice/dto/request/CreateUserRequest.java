package ru.example.userservice.dto.request;

public record CreateUserRequest(
        String name,
        String email,
        Long category
) {
}
