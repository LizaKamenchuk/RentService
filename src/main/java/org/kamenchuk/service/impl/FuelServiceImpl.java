package org.kamenchuk.service.impl;

import org.kamenchuk.repository.FuelRepository;
import org.kamenchuk.models.Fuel;
import org.kamenchuk.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
        return fuelRepository.findByFuelType(fuelType);
    }
}
