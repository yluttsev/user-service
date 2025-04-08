package ru.example.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.userservice.dto.PurchaseDto;
import ru.example.userservice.dto.request.CreatePurchaseRequest;
import ru.example.userservice.entity.Product;
import ru.example.userservice.entity.Purchase;
import ru.example.userservice.entity.User;
import ru.example.userservice.mapper.PurchaseMapper;
import ru.example.userservice.repository.PurchaseRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserService userService;
    private final ProductService productService;
    private final PurchaseMapper purchaseMapper;

    @Transactional
    public PurchaseDto create(CreatePurchaseRequest createPurchaseRequest, Long userId) {
        List<Product> products = createPurchaseRequest.productIds()
                .stream()
                .map(productService::getEntityById)
                .toList();
        BigDecimal totalAmount = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        User user = userService.getEntityById(userId);
        Purchase purchase = Purchase.builder()
                .amount(totalAmount)
                .user(user)
                .products(products)
                .build();
        user.setTotalSpent(user.getTotalSpent().add(totalAmount));
        return purchaseMapper.mapEntityToDto(purchaseRepository.save(purchase));
    }

    public List<PurchaseDto> getByUser(Long userId, int pageNumber, int pageSize) {
        return purchaseRepository.findByUserId(userId, PageRequest.of(pageNumber, pageSize))
                .getContent()
                .stream()
                .map(purchaseMapper::mapEntityToDto)
                .toList();
    }
}
