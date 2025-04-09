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

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserService userService;
    private final PurchaseMapper purchaseMapper;
    private final PurchaseItemService purchaseItemService;
    private final ProductClient productClient;

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
        return purchaseMapper.mapEntityToDto(purchase);
    }

    private List<PurchaseItem> createEntitiesFromProductDto(List<ProductDto> productDtos) {
        List<PurchaseItem> purchaseItems = productDtos.stream()
                .map(product -> PurchaseItem.builder()
                        .productId(product.id())
                        .build())
                .toList();
        return purchaseItemService.saveAll(purchaseItems);
    }

    private BigDecimal calculateTotalAmount(List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(ProductDto::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void updateUserTotalSpent(User user, BigDecimal totalSpent) {
        user.setTotalSpent(user.getTotalSpent().add(totalSpent));
        userService.save(user);
    }

    public List<PurchaseDto> getByUser(Long userId, int pageNumber, int pageSize) {
        return purchaseRepository.findByUserId(userId, PageRequest.of(pageNumber, pageSize))
                .getContent()
                .stream()
                .map(purchaseMapper::mapEntityToDto)
                .toList();
    }
}
