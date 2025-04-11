package ru.example.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.userservice.entity.PurchaseItem;

/**
 * Репозиторий для работы с {@link PurchaseItem}
 */
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

    /**
     * Проверка существования продукта по ID
     *
     * @param productId ID продукта
     * @return true, если продукт существует, иначе false
     */
    boolean existsByProductId(Long productId);
}
