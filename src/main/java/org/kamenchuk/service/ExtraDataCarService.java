package org.kamenchuk.service;

import org.kamenchuk.dto.carDTO.extraDataCarDTO.ExtraDataCarUpdateRequest;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.models.ExtraDataCar;

/**
 * Interface ExtraDataCarService for work with ExtraDataCar
 *
 * @author Lisa Kamenchuk
 */
public interface ExtraDataCarService {
    /**
     * save extraDataCar
     *
     * @param extraDataCar
     * @return extraDataCar
     */
    ExtraDataCar save(ExtraDataCar extraDataCar) throws CreationException;

    /**
     * delete extraDataCar
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * get extraDataCar by id
     *
     * @param id
     * @return extraDataCar
     * @throws ResourceNotFoundException
     */
    ExtraDataCar getById(Integer id) throws ResourceNotFoundException;

    ExtraDataCar update(Integer id, ExtraDataCarUpdateRequest newExtraDataCar) throws UpdatingException;
}
