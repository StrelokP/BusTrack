package ru.aptech.bustrack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aptech.bustrack.entities.Transport;
import ru.aptech.bustrack.repositories.TransportRepository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class TransportService {
    @Autowired
    protected TransportRepository transportRepository;

    public Optional<Transport> getTransportById(Long id) {
        return transportRepository.findById(id);
    }

    public List<Transport> getTransports() {
        return transportRepository.findAll();
    }

    public void deleteTransportById(Long id) {
        transportRepository.deleteById(id);
    }

    public Transport saveTransport(Transport transport) {
        return transportRepository.save(transport);
    }

}
