package ru.example.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.example.userservice.dto.UserDto;
import ru.example.userservice.entity.User;

/**
 * Маппер для {@link User}
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDto mapEntityToDto(User user);
}
