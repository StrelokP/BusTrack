package ru.aptech.bustrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aptech.bustrack.entities.Route;
@SuppressWarnings("unused")
public interface RouteRepository extends JpaRepository<Route, Long> {
}