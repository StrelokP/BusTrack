package ru.aptech.bustrack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aptech.bustrack.entities.Route;
import ru.aptech.bustrack.repositories.RouteRepository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class RouteService {
    @Autowired
    protected RouteRepository routeRepository;

    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    public void deleteRouteById(Long id) {
        routeRepository.deleteById(id);
    }

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

}
