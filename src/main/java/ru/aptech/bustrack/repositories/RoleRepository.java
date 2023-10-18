package ru.aptech.bustrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aptech.bustrack.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}