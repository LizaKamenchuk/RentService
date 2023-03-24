package org.kamenchuk.service;

import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.exceptions.ResourceNotFoundException;

public interface ExtraUsersService {

    /**
     * Gets extraUsersData by id
     * @param id - extraUsersData id
     * @return ExtraUserDataUpdateRequest
     */
    ExtraUserDataUpdateRequest getExtraDataById(Long id) throws ResourceNotFoundException;

    /**
     * Updates extraUsersData
     * @param request - ExtraUserDataUpdateRequest entity
     * @param idUser - extraUsersData id from DB
     * @return ExtraUserDataUpdateRequest
     */
    ExtraUserDataUpdateRequest updateExtraData(ExtraUserDataUpdateRequest request,Long idUser);


}
