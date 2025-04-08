package ru.example.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.example.userservice.dto.ProductDto;
import ru.example.userservice.entity.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductDto mapEntityToDto(Product product);
}
