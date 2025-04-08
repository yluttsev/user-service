package ru.example.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.example.userservice.dto.PurchaseDto;
import ru.example.userservice.entity.Purchase;

import java.time.Instant;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseMapper {

    PurchaseDto mapEntityToDto(Purchase purchase);

    default long instantToTimestamp(Instant instant) {
        return instant.getEpochSecond();
    }
}
