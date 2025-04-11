package ru.example.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.example.userservice.dto.ProductDto;
import ru.example.userservice.dto.PurchaseDto;
import ru.example.userservice.entity.Purchase;

import java.time.Instant;
import java.util.List;

/**
 * Маппер для {@link Purchase}
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseMapper {

    @Mapping(target = "products", source = "productDtos")
    PurchaseDto mapEntityToDto(Purchase purchase, List<ProductDto> productDtos);

    default long instantToTimestamp(Instant instant) {
        return instant.getEpochSecond();
    }
}
