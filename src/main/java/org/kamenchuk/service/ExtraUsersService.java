package org.kamenchuk.service;

import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.dto.extraUsersDataDTO.ExtraUsersDataCreateRequest;
import org.kamenchuk.models.ExtraUsersData;

public interface ExtraUsersService {

    ExtraUserDataUpdateRequest getExtraDataById(Long id);
    ExtraUserDataUpdateRequest updateExtraData(ExtraUserDataUpdateRequest request);
    ExtraUsersData createExtra(ExtraUsersDataCreateRequest create);

}
