package org.kamenchuk.service;

import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Interface CarService for work with Car
 * @author Lisa Kamechuk
 */
//TODO где JAVA DOC?
public interface CarService {

//   void kafkaProducer(CarCreateRequest id);

    /**
     * Gets all cars from DB
     * @return CarResponse
     */
    List<CarResponse> getAll();

    /**
     * Creates car
     * @param request - CarCreateRequest entity
     * @return CarResponse
     */
    CarResponse create(CarCreateRequest request, MultipartFile file) throws CreationException;

    /**
     * Gets car by number
     * @param carNumber - cars number
     * @return CarResponse
     */
    CarResponse getCarByNumber(String carNumber) throws ResourceNotFoundException;

    /**
     * Deletes car by id
     * @param idCar - cars id from DB
     */
    void deleteById(Integer idCar);

    /**
     * Deletes car by number
     * @param carNumber  - cars number
     */
    void deleteByCarNumber(String carNumber);

    /**
     * Updates car
     * @param request CarUpdateRequest entity
     * @param idCar - cars id from DB
     * @return CarResponse
     */
    CarResponse update(CarUpdateRequest request, Integer idCar) throws UpdatingException;
}
