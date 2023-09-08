package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.extraDataCarDTO.CarClassDto;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.mapper.CarClassMapper;
import org.kamenchuk.models.CarClass;
import org.kamenchuk.repository.CarClassRepository;
import org.kamenchuk.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class CarClassServiceImpl implements CarClassService {

    private final CarClassRepository carClassRepository;
    private final CarClassMapper carClassMapper;

    @Autowired
    public CarClassServiceImpl(CarClassRepository carClassRepository,
                               CarClassMapper carClassMapper) {
        this.carClassRepository = carClassRepository;
        this.carClassMapper = carClassMapper;
    }

    @Override
    public CarClassDto save(CarClassDto carClassType) throws CreationException {
        return Optional.ofNullable(carClassType)
                .map(carClassMapper::toModel)
                .map(carClassRepository::save)
                .map(carClassMapper::toDto)
                .orElseThrow(() -> {
                    log.info(String.format("CarClass with type %s is not created", carClassType));
                    throw new CreationException(String.format("CarClass with type %s is not created", carClassType));
                });

    }

    @Override
    public void deleteById(Integer id) {
        carClassRepository.deleteById(id);
    }

    @Override
    public CarClass findByCarClassType(String carClassType) {
        return carClassRepository.findByClassType(carClassType).orElse(null);
    }

    public CarClass findByCarClassTypeOrFail(String carClassType) throws ResourceNotFoundException {
        return carClassRepository.findByClassType(carClassType).orElseThrow(() -> {
            log.info("Car class type " + carClassType + " does not exist");
            throw new ResourceNotFoundException(String.format("Car class type %s does not exist", carClassType));
        });
    }


}
