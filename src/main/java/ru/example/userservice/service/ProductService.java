package ru.example.userservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.userservice.dto.ProductDto;
import ru.example.userservice.dto.request.CreateProductRequest;
import ru.example.userservice.entity.Product;
import ru.example.userservice.mapper.ProductMapper;
import ru.example.userservice.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto create(CreateProductRequest createProductRequest) {
        Product product = Product.builder()
                .name(createProductRequest.name())
                .category(createProductRequest.category())
                .price(createProductRequest.price())
                .build();
        product = productRepository.save(product);
        return productMapper.mapEntityToDto(product);
    }

    public Product getEntityById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product with id '%d' not found".formatted(id))
        );
    }

    public ProductDto getDtoById(Long id) {
        Product product = getEntityById(id);
        return productMapper.mapEntityToDto(product);
    }
}
