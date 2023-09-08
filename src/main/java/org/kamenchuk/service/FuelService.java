package org.kamenchuk.service;

import org.kamenchuk.dto.extraDataCarDTO.FuelDto;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.Fuel;

/**
 * Interface FuelService for work with Fuel
 *
 * @author Lisa Kamenchuk
 */
public interface FuelService {
    /**
     * save fuel
     *
     * @param fuelType
     * @return saved fuelType
     */
    FuelDto save(FuelDto fuelType) throws CreationException;

    /**
     * delete fuel by id
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * get fuel by fuel type
     *
     * @param fuelType
     * @return fuel
     * @throws ResourceNotFoundException
     */
    Fuel findByFuelTypeOrFail(String fuelType) throws ResourceNotFoundException;

    Fuel findByFuelType(String fuelDto);

}
