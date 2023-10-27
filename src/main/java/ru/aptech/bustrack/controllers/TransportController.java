package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aptech.bustrack.entities.Transport;
import ru.aptech.bustrack.services.TransportService;

import java.util.Optional;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api")
public class TransportController {
    @Autowired
    protected TransportService transportService;

    @GetMapping("/transport")
    public ResponseEntity<?> getTransportById(@RequestParam(name = "id") Long id) {
        Optional<Transport> transport = transportService.getTransportById(id);

        if (transport.isPresent()) {
            return ResponseEntity.ok(transport.get());
        } else {
            return ResponseEntity.badRequest().body("Транспорт не найден");
        }
    }

    @GetMapping("/transports")
    public ResponseEntity<?> getTransports() {
        return ResponseEntity.ok(transportService.getTransports());
    }

    @PostMapping("/transport")
    public void saveTransport(@RequestBody Transport transport) {
        transportService.saveTransport(transport);
    }

    @DeleteMapping("/transport")
    public void deleteTransport(@RequestParam(name = "id") Long id) {
        transportService.deleteTransportById(id);
    }

}
