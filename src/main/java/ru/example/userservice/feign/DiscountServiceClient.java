package ru.example.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Feign клиент для общения с Discount Service
 */
@FeignClient(name = "discount-service-client", url = "${discount.service.url}")
public interface DiscountServiceClient {

    /**
     * Расчет фиксированной скидки
     *
     * @param price             Стоимость покупки
     * @param productCategoryId ID категории продукта
     * @param clientCategoryId  ID категории клиента
     * @return сумма покупки с учетом скидок
     */
    @GetMapping("/discount/fixed")
    BigDecimal getFixedDiscount(
            @RequestParam("price") BigDecimal price,
            @RequestParam("product_category") long productCategoryId,
            @RequestParam("client_category") long clientCategoryId);

    /**
     * Расчет переменной скидки
     *
     * @param price             Стоимость покупки
     * @param productCategoryId ID категории продукта
     * @param clientCategoryId  ID категории клиента
     * @return сумма покупки с учетом скидок
     */
    @GetMapping("/discount/variable")
    BigDecimal getVariableDiscount(
            @RequestParam("price") BigDecimal price,
            @RequestParam("product_category") long productCategoryId,
            @RequestParam("client_category") long clientCategoryId);

}
