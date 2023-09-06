package org.kamenchuk.service.impl;

import org.kamenchuk.repository.CarClassRepository;
import org.kamenchuk.models.CarClass;
import org.kamenchuk.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
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
        return carClassRepository.findByClassType(carClassType);
    }
}
