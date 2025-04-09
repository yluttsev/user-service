package ru.example.userservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.userservice.dto.UserDto;
import ru.example.userservice.dto.request.CreateUserRequest;
import ru.example.userservice.entity.User;
import ru.example.userservice.mapper.UserMapper;
import ru.example.userservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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

    public User save(User user) {
        return userRepository.save(user);
    }

    public UserDto getDtoById(Long id) {
        User user = getEntityById(id);
        return userMapper.mapEntityToDto(user);
    }

    public User getEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id '%d' not found".formatted(id))
        );
    }
}
