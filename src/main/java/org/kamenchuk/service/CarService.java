package org.kamenchuk.service;

import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;

import java.util.List;

public interface CarService {

    List<CarResponse> getAll();
    CarResponse create(CarCreateRequest request);
    CarResponse getCarByNumber(String carNumber);
    void deleteById(Integer idCar);
    void deleteByCarNumber(String carNumber);

    CarResponse update(CarResponse request);
}
