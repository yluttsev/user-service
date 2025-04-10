package ru.example.userservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Swagger
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI userServiceOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("User Service API")
                .version("1.0.0")
                .description("Система работы с пользователями и их покупками")
        );
    }
}
