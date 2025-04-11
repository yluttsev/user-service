package ru.example.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.example.userservice.dto.ProductDto;

import java.util.List;

@FeignClient(name = "product-client", url = "${recommendation.service.url}")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductDto> getAllById(@RequestParam("id") List<Long> ids);
}
