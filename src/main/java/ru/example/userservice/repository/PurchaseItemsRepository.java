package ru.example.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.userservice.entity.Purchase;
import ru.example.userservice.entity.PurchaseItems;

import java.util.List;

public interface PurchaseItemsRepository extends JpaRepository<PurchaseItems, Long> {
    List<PurchaseItems> findByPurchase(Purchase purchase);
    List<PurchaseItems> findByPurchaseId(Long purchaseId);
    List<PurchaseItems> findByProductId(Long productId);
    boolean existsByPurchaseIdAndProductId(Long purchaseId, Long productId);
}
