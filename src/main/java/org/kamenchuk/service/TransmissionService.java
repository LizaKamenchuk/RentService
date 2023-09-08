package org.kamenchuk.service;

import org.kamenchuk.dto.carDTO.extraDataCarDTO.TransmissionDto;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.models.Transmission;

/**
 * Interface TransmissionService for work with Transmission
 *
 * @author Lisa Kamenchuk
 */
public interface TransmissionService {
    /**
     * save new transmission type
     *
     * @param transmissionDto
     * @return saved transmission type
     */
    TransmissionDto save(TransmissionDto transmissionDto) throws CreationException;

    /**
     * delete transmission by id
     *
     * @param id - transmissions id
     */
    void deleteById(Integer id);

    /**
     * get transmission by transmission type
     *
     * @param transmissionType
     * @return transmission
     */
    Transmission findByTransmissionTypeOrFail(String transmissionType);
   Transmission findByTransmissionType(String transmissionType);
}
