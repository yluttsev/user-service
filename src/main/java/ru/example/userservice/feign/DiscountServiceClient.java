package ru.example.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "discount-service-client", url = "${discount.service.url}")
public interface DiscountServiceClient {

    @GetMapping("/discount/fixed")
    BigDecimal getFixedDiscount(
            @RequestParam("price") BigDecimal price,
            @RequestParam("product_category") long productCategoryId,
            @RequestParam("client_category") long clientCategoryId);

    @GetMapping("/discount/variable")
    BigDecimal getVariableDiscount(
            @RequestParam("price") BigDecimal price,
            @RequestParam("product_category") long productCategoryId,
            @RequestParam("client_category") long clientCategoryId);

}
