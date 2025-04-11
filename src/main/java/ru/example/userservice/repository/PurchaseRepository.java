package ru.example.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.example.userservice.entity.Purchase;

/**
 * Репозиторий для работы с {@link Purchase}
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    /**
     * Получение страницы с покупками пользователя по его ID
     *
     * @param userId   ID пользователя
     * @param pageable {@link Pageable}
     * @return страница с покупками пользователя
     */
    @Query("from Purchase p where p.user.id = :user_id")
    Page<Purchase> findByUserId(@Param("user_id") Long userId, Pageable pageable);
}
