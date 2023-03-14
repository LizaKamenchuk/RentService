package org.kamenchuk.dto.mapper;

import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.dto.extraUsersDataDTO.ExtraUsersDataCreateRequest;
import org.kamenchuk.models.ExtraUsersData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExtraUsersDataMapper {
    ExtraUsersData toUser(ExtraUserDataUpdateRequest request);
    ExtraUserDataUpdateRequest toDto(ExtraUsersData extraUsersData);

    ExtraUsersData toUser(ExtraUsersDataCreateRequest create);
}
