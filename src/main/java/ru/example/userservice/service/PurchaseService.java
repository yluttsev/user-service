package ru.example.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.userservice.dto.ProductDto;
import ru.example.userservice.dto.PurchaseDto;
import ru.example.userservice.dto.request.CreatePurchaseRequest;
import ru.example.userservice.entity.Purchase;
import ru.example.userservice.entity.PurchaseItem;
import ru.example.userservice.entity.User;
import ru.example.userservice.feign.ProductClient;
import ru.example.userservice.mapper.PurchaseMapper;
import ru.example.userservice.repository.PurchaseRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Сервис для работы с {@link Purchase}
 */
@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserService userService;
    private final PurchaseMapper purchaseMapper;
    private final PurchaseItemService purchaseItemService;
    private final ProductClient productClient;

    /**
     * Создание покупки для пользователя. <br>
     * Принимает список ID продуктов, получает их DTO из Recommendation Service, затем считает их общую стоимость и прибавляет ее к totalAmount пользователя.
     *
     * @param createPurchaseRequest Запрос на создание покупки
     * @param userId                ID пользователя, для которого создается покупка
     * @return {@link PurchaseDto DTO} созданной покупки
     */
    @Transactional
    public PurchaseDto create(CreatePurchaseRequest createPurchaseRequest, Long userId) {
        List<ProductDto> products = productClient.getAllById(createPurchaseRequest.productIds());
        List<PurchaseItem> purchaseItems = createEntitiesFromProductDto(products);
        BigDecimal totalAmount = calculateTotalAmount(products);
        User user = userService.getEntityById(userId);
        updateUserTotalSpent(user, totalAmount);
        Purchase purchase = Purchase.builder()
                .amount(totalAmount)
                .user(user)
                .products(purchaseItems)
                .build();
        purchase = purchaseRepository.save(purchase);
        return purchaseMapper.mapEntityToDto(purchase, products);
    }

    /**
     * Создает в БД {@link PurchaseItem} для каждого продукта из Recommendation Service, исключая уже существующие
     *
     * @param productDtos Список {@link ProductDto DTO} продуктов
     * @return Список созданных {@link PurchaseItem}
     */
    private List<PurchaseItem> createEntitiesFromProductDto(List<ProductDto> productDtos) {
        List<PurchaseItem> purchaseItems = productDtos.stream()
                .map(product -> PurchaseItem.builder()
                        .productId(product.id())
                        .build())
                .filter(purchaseEntity -> !purchaseItemService.existsByProductId(purchaseEntity.getProductId()))
                .toList();
        return purchaseItemService.saveAll(purchaseItems);
    }

    /**
     * Считает общую стоимость продуктов
     *
     * @param productDtos Список {@link ProductDto DTO} продуктов
     * @return общая стоимость продуктов
     */
    private BigDecimal calculateTotalAmount(List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(ProductDto::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Обновляет totalSpent у {@link User}
     *
     * @param user       Пользователь
     * @param totalSpent Общая сумма покупок
     */
    private void updateUserTotalSpent(User user, BigDecimal totalSpent) {
        user.setTotalSpent(user.getTotalSpent().add(totalSpent));
        userService.save(user);
    }

    public List<PurchaseDto> getByUser(Long userId, int pageNumber, int pageSize) {
        return purchaseRepository.findByUserId(userId, PageRequest.of(pageNumber, pageSize))
                .getContent()
                .stream()
                .map(purchase -> {
                    List<Long> productIds = purchase.getProducts()
                            .stream()
                            .map(PurchaseItem::getProductId)
                            .toList();
                    List<ProductDto> products = productClient.getAllById(productIds);
                    return purchaseMapper.mapEntityToDto(purchase, products);
                })
                .toList();
    }
}
