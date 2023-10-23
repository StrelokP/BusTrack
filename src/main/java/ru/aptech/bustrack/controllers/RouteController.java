package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aptech.bustrack.entities.Route;
import ru.aptech.bustrack.services.RouteService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RouteController {
    @Autowired
    protected RouteService routeService;

    @GetMapping("/route")
    public ResponseEntity<?> getRouteById(@RequestParam(name = "id") Long id) {
        Optional<Route> route = routeService.getRouteById(id);

        if (route.isPresent()) {
            return ResponseEntity.ok(route.get());
        } else {
            return ResponseEntity.badRequest().body("Маршрут не найден");
        }
    }

    @GetMapping("/routes")
    public ResponseEntity<?> getRoutes() {
        return ResponseEntity.ok(routeService.getRoutes());
    }

    @PostMapping("/route")
    public void saveRoute(@RequestBody Route route) {
        routeService.saveRoute(route);
    }

    @DeleteMapping("/route")
    public void deleteRoute(@RequestParam(name = "id") Long id) {
        routeService.deleteRouteById(id);
    }

}
