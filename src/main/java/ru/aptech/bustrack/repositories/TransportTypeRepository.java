package ru.aptech.bustrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aptech.bustrack.entities.TransportType;

public interface TransportTypeRepository extends JpaRepository<TransportType, Long> {
}