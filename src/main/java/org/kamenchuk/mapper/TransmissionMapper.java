package org.kamenchuk.mapper;

import org.kamenchuk.dto.extraDataCarDTO.TransmissionDto;
import org.kamenchuk.models.Transmission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransmissionMapper {
    Transmission toModel(TransmissionDto dto);

    TransmissionDto toDto(Transmission transmission);
}
