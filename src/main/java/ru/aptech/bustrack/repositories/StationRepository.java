package ru.aptech.bustrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aptech.bustrack.entities.Station;

public interface StationRepository extends JpaRepository<Station, Long> {
}