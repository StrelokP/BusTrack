package ru.aptech.bustrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aptech.bustrack.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    boolean existsByLoginIgnoreCase(String login);
}