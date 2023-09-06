package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.repository.FuelRepository;
import org.kamenchuk.models.Fuel;
import org.kamenchuk.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FuelServiceImpl implements FuelService {

    private final FuelRepository fuelRepository;

    @Autowired
    public FuelServiceImpl(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    @Override
    public String save(String fuelType) {
        return fuelRepository.save(Fuel.builder()
                        .fuelType(fuelType)
                        .build())
                .getFuelType();
    }

    @Override
    public void deleteById(Integer id) {
        fuelRepository.deleteById(id);
    }

    @Override
    public Fuel findByFuelType(String fuelType) {
        Optional<Fuel> fuel = fuelRepository.findByFuelType(fuelType);
        if (fuel.isEmpty()) {
            log.info("Fuel type " + fuelType + " does not exist");
            throw new ResourceNotFoundException("Fuel type " + fuelType + " does not exist");
        } else return fuel.get();
    }
}
