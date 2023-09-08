package org.kamenchuk.service;

import org.kamenchuk.dto.extraDataCarDTO.CarClassDto;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.CarClass;

/**
 * Interface CarClassService for work with CarClass
 *
 * @author Lisa Kamenchuk
 */
public interface CarClassService {
    /**
     * save carClassType
     *
     * @param carClassType
     * @return saved carClassType
     */
    CarClassDto save(CarClassDto carClassType) throws CreationException;

    /**
     * delete by id
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * get carClass by class type
     *
     * @param carClassType
     * @return CarClass
     * @throws ResourceNotFoundException
     */
    CarClass findByCarClassTypeOrFail(String carClassType) throws ResourceNotFoundException;

    CarClass findByCarClassType(String carClassType);
}
