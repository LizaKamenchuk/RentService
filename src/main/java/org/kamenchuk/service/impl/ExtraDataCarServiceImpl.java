package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.CarClass;
import org.kamenchuk.models.ExtraDataCar;
import org.kamenchuk.models.Fuel;
import org.kamenchuk.models.Transmission;
import org.kamenchuk.repository.ExtraDataCarRepository;
import org.kamenchuk.service.CarClassService;
import org.kamenchuk.service.ExtraDataCarService;
import org.kamenchuk.service.FuelService;
import org.kamenchuk.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ExtraDataCarServiceImpl implements ExtraDataCarService {
    private final ExtraDataCarRepository extraDataCarRepository;
    private final TransmissionService transmissionService;
    private final CarClassService carClassService;
    private final FuelService fuelService;

    @Autowired
    public ExtraDataCarServiceImpl(ExtraDataCarRepository extraDataCarRepository,
                                   TransmissionService transmissionService,
                                   CarClassService carClassService,
                                   FuelService fuelService) {
        this.extraDataCarRepository = extraDataCarRepository;
        this.transmissionService = transmissionService;
        this.carClassService = carClassService;
        this.fuelService = fuelService;
    }

    @Override
    public ExtraDataCar save(ExtraDataCar extraDataCar) {
        Fuel fuel = fuelService.findByFuelType(
                extraDataCar.getFuel().getFuelType());
        CarClass carClass = carClassService.findByCarClassType(
                extraDataCar.getCarClass().getClassType());
        Transmission transmission = transmissionService.findByTransmissionType(
                extraDataCar.getTransmission().getTransmissionType());
        extraDataCar.setCarClass(carClass);
        extraDataCar.setFuel(fuel);
        extraDataCar.setTransmission(transmission);
        return extraDataCarRepository.save(extraDataCar);
    }

    @Override
    public void delete(Integer id) {
        extraDataCarRepository.deleteById(id);
    }

    @Override
    public ExtraDataCar getById(Integer id) {
        Optional<ExtraDataCar> extraDataCar = extraDataCarRepository.findById(id);
        if (extraDataCar.isEmpty()) {
            log.info("Extra data with id " + id + "does not exist. ExtraDataCarServiceImpl-class, getById-method");
            throw new ResourceNotFoundException("Extra data with id " + id + "does not exist");
        }
        return extraDataCar.get();
    }


}
