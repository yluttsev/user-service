package ru.example.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.userservice.entity.PurchaseItem;
import ru.example.userservice.repository.PurchaseItemRepository;

import java.util.List;

/**
 * Сервис для работы с {@link PurchaseItem}
 */
@Service
@RequiredArgsConstructor
public class PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;

    /**
     * Сохранение списка {@link PurchaseItem}
     *
     * @param purchaseItems список {@link PurchaseItem}
     * @return список сохраненных {@link PurchaseItem}
     */
    public List<PurchaseItem> saveAll(List<PurchaseItem> purchaseItems) {
        return purchaseItemRepository.saveAll(purchaseItems);
    }

    /**
     * Проверка существования продукта по ID
     *
     * @param productId ID продукта
     * @return true, если продукт существует, иначе false
     */
    public boolean existsByProductId(Long productId) {
        return purchaseItemRepository.existsByProductId(productId);
    }
}
