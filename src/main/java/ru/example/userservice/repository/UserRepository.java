package ru.example.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.userservice.entity.User;

/**
 * Репозиторий для работы с {@link User}
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Проверкам существования пользователя по его email
     *
     * @param email Email пользователя
     * @return true, если пользователь существует, иначе false
     */
    boolean existsByEmail(String email);
}
