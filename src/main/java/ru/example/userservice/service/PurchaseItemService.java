package ru.example.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.userservice.entity.PurchaseItem;
import ru.example.userservice.repository.PurchaseItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;

    public List<PurchaseItem> saveAll(List<PurchaseItem> purchaseItems) {
        return purchaseItemRepository.saveAll(purchaseItems);
    }

    public boolean existsByProductId(Long productId) {
        return purchaseItemRepository.existsByProductId(productId);
    }
}
