package ru.aptech.bustrack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aptech.bustrack.entities.TransportType;
import ru.aptech.bustrack.repositories.TransportTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransportTypeService {
    @Autowired
    protected TransportTypeRepository transportTypeRepository;

    public Optional<TransportType> getTransportTypeById(Long id) {
        return transportTypeRepository.findById(id);
    }

    public List<TransportType> getTransportTypes() {
        return transportTypeRepository.findAll();
    }

    public void deleteTransportTypeById(Long id) {
        transportTypeRepository.deleteById(id);
    }

    public TransportType saveTransportType(TransportType transportType) {
        return transportTypeRepository.save(transportType);
    }

}
