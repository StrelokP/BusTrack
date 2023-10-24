package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aptech.bustrack.entities.Station;
import ru.aptech.bustrack.services.StationService;

import java.util.Optional;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api")
public class StationController {
    @SuppressWarnings("unused")
    @Autowired
    protected StationService stationService;

    @SuppressWarnings("unused")
    @GetMapping("/station")
    public ResponseEntity<?> getStationById(@RequestParam(name = "id") Long id) {
        Optional<Station> station = stationService.getStationById(id);

        if (station.isPresent()) {
            return ResponseEntity.ok(station.get());
        } else {
            return ResponseEntity.badRequest().body("Остановка не найдена");
        }
    }

    @SuppressWarnings("unused")
    @GetMapping("/stations")
    public ResponseEntity<?> getStations() {
        return ResponseEntity.ok(stationService.getStations());
    }

    @SuppressWarnings("unused")
    @PostMapping("/station")
    public void saveStation(@RequestBody Station station) {
        stationService.saveStation(station);
    }

    @SuppressWarnings("unused")
    @DeleteMapping("/station")
    public void deleteStation(@RequestParam(name = "id") Long id) {
        stationService.deleteStationById(id);
    }

}
