package ru.aptech.bustrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aptech.bustrack.entities.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
}