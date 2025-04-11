package ru.example.userservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.userservice.dto.UserDto;
import ru.example.userservice.dto.request.CreateUserRequest;
import ru.example.userservice.entity.User;
import ru.example.userservice.mapper.UserMapper;
import ru.example.userservice.repository.UserRepository;

/**
 * Сервис для работы с {@link User}
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Создание пользователя. Проверяет email на существование, затем создает {@link User}
     *
     * @param createUserRequest Запрос на создание пользователя
     * @return {@link UserDto DTO} созданного пользователя
     */
    public UserDto create(CreateUserRequest createUserRequest) {
        if (userRepository.existsByEmail(createUserRequest.email())) {
            throw new EntityNotFoundException("User with email '%s' already exists".formatted(createUserRequest.email()));
        }
        User user = User.builder()
                .email(createUserRequest.email())
                .name(createUserRequest.name())
                .category(createUserRequest.category())
                .build();
        user = userRepository.save(user);
        return userMapper.mapEntityToDto(user);
    }

    /**
     * Сохранение пользователя
     *
     * @param user Пользователь
     * @return сохраненный пользователь
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Получение пользователя по его ID и маппинг в {@link UserDto}
     *
     * @param id ID пользователя
     * @return {@link UserDto DTO} пользователя
     */
    public UserDto getDtoById(Long id) {
        User user = getEntityById(id);
        return userMapper.mapEntityToDto(user);
    }

    /**
     * Получение пользователя по его ID
     *
     * @param id ID пользователя
     * @return найденный пользователь
     */
    public User getEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id '%d' not found".formatted(id))
        );
    }
}
