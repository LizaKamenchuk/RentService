package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.carDTO.extraDataCarDTO.FuelDto;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.mapper.FuelMapper;
import org.kamenchuk.models.Fuel;
import org.kamenchuk.repository.FuelRepository;
import org.kamenchuk.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FuelServiceImpl implements FuelService {

    private final FuelRepository fuelRepository;
    private final FuelMapper fuelMapper;

    @Autowired
    public FuelServiceImpl(FuelRepository fuelRepository,
                           FuelMapper fuelMapper) {
        this.fuelRepository = fuelRepository;
        this.fuelMapper = fuelMapper;
    }

    @Override
    public FuelDto save(FuelDto fuelDto) throws CreationException {
        return Optional.ofNullable(fuelDto)
                .map(fuelMapper::toModel)
                .map(fuelRepository::save)
                .map(fuelMapper::toDto)
                .orElseThrow(() -> {
                    throw new CreationException(String.format("Fuel type is not created"));
                });
    }

    @Override
    public void deleteById(Integer id) {
        fuelRepository.deleteById(id);
    }

    @Override
    public Fuel findByFuelTypeOrFail(String fuelType) throws ResourceNotFoundException {
        return fuelRepository.findFuelByFuelType(fuelType).orElseThrow(() -> {
            throw new ResourceNotFoundException(String.format("Fuel %s does not exist", fuelType));
        });
    }

    @Override
    public Fuel findByFuelType(String fuelType) {
        return fuelRepository.findFuelByFuelType(fuelType).orElse(null);
    }

}
