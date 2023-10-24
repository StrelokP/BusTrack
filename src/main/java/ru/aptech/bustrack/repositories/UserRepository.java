package ru.aptech.bustrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aptech.bustrack.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginIgnoreCase(String login);
}