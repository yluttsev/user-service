package ru.example.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.userservice.entity.PurchaseItem;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
}
