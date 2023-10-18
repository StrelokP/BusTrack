package ru.aptech.bustrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aptech.bustrack.entities.Transport;

public interface TransportRepository extends JpaRepository<Transport, Long> {
}