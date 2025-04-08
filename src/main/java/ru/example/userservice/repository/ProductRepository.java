package ru.example.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.userservice.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Long categoryId);
}
