package ru.example.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.userservice.entity.Purchase;
import ru.example.userservice.entity.User;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUser(User user);
}
