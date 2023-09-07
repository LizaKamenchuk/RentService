package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.repository.CarClassRepository;
import org.kamenchuk.models.CarClass;
import org.kamenchuk.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class CarClassServiceImpl implements CarClassService {

    private final CarClassRepository carClassRepository;

    @Autowired
    public CarClassServiceImpl(CarClassRepository carClassRepository) {
        this.carClassRepository = carClassRepository;
    }

    @Override
    public String save(String carClassType) {
        return carClassRepository.save(
                        CarClass.builder()
                                .classType(carClassType)
                                .build())
                .getClassType();
    }

    @Override
    public void deleteById(Integer id) {
        carClassRepository.deleteById(id);
    }

    @Override
    public CarClass findByCarClassType(String carClassType) {
        Optional<CarClass> carClass = carClassRepository.findByClassType(carClassType);
        if (carClass.isEmpty()) {
            log.info("Car class type " + carClassType + " does not exist");
//            throw new ResourceNotFoundException("Car class type " + carClassType + " does not exist");
            throw new ResourceNotFoundException(String.format("Car class type %s does not exist", carClassType));
        } else return carClass.get();
    }
}
