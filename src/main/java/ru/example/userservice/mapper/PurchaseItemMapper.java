package ru.example.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.example.userservice.dto.PurchaseItemDto;
import ru.example.userservice.entity.PurchaseItem;

/**
 * Маппер для {@link PurchaseItem}
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseItemMapper {

    PurchaseItemDto mapEntityToDto(PurchaseItem purchaseItem);
}
