package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.extraDataCarDTO.ExtraDataCarUpdateRequest;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
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
    public ExtraDataCar save(ExtraDataCar extraDataCar) throws CreationException {
        Fuel fuel = fuelService.findByFuelTypeOrFail(extraDataCar.getFuel().getFuelType());
        CarClass carClass = carClassService.findByCarClassTypeOrFail(extraDataCar.getCarClass().getClassType());
        Transmission transmission = transmissionService.findByTransmissionTypeOrFail(extraDataCar.getTransmission().getTransmissionType());
        extraDataCar.setCarClass(carClass);
        extraDataCar.setFuel(fuel);
        extraDataCar.setTransmission(transmission);
        return Optional.of(extraDataCar)
                .map(extraDataCarRepository::save)
                .orElseThrow(() -> {
                    throw new CreationException("ExtraDataCar is not created");
                });
    }

    @Override
    public void delete(Integer id) {
        extraDataCarRepository.deleteById(id);
    }

    @Override
    public ExtraDataCar getById(Integer id) throws ResourceNotFoundException {
        return extraDataCarRepository.findById(id)
                .orElseThrow(() -> {
                    log.info(String.format("Extra data with id %s does not exist.ExtraDataCarServiceImpl-class, getById-method", id));
                    throw new ResourceNotFoundException(String.format("Extra data with id %s does not exist", id));
                });
    }

    @Override
    public ExtraDataCar update(Integer id, ExtraDataCarUpdateRequest newExtraDataCar) throws UpdatingException {
        return extraDataCarRepository.findById(id)
                .map(oldExtraDataCar -> updateInfo(oldExtraDataCar, newExtraDataCar))
                .map(oldExtraDataCar -> {
                    Fuel newFuel = fuelService.findByFuelType(newExtraDataCar.getFuelDto().getFuelType());
                    if (newFuel != null) oldExtraDataCar.setFuel(newFuel);
                    return oldExtraDataCar;
                })
                .map(oldExtraDataCar -> {
                    Transmission newTransmission = transmissionService.findByTransmissionType(newExtraDataCar.getTransmissionDto().getTransmissionType());
                    if (newTransmission != null) oldExtraDataCar.setTransmission(newTransmission);
                    return oldExtraDataCar;
                })
                .map(oldExtraDataCar -> {
                    if (newExtraDataCar.getCarClassDto() != null && !newExtraDataCar.getCarClassDto().getCarClassType().isEmpty()) {
                        oldExtraDataCar.setCarClass(carClassService.findByCarClassType(newExtraDataCar.getCarClassDto().getCarClassType()));
                    }
                    return oldExtraDataCar;
                })
                .map(extraDataCarRepository::save)
                .orElseThrow(() -> {
                    throw new UpdatingException("ExtraDataCar is not changed");
                });

    }

    private ExtraDataCar updateInfo(ExtraDataCar oldData, ExtraDataCarUpdateRequest newData) {
        return ExtraDataCar.builder()
                .id(oldData.getId())
                .fuel_consumption((newData.getFuel_consumption() == null || !newData.getFuel_consumption().isNaN()) ? oldData.getFuel_consumption() : newData.getFuel_consumption())
                .manufacture_year((newData.getManufacture_year() == null || newData.getManufacture_year().equals(0)) ? oldData.getManufacture_year() : newData.getManufacture_year())
                .limitations((newData.getLimitations() == null || newData.getLimitations().isEmpty()) ? oldData.getLimitations() : newData.getLimitations())
                .transmission(oldData.getTransmission())
                .fuel(oldData.getFuel())
                .carClass(oldData.getCarClass())
                .build();
    }


}
