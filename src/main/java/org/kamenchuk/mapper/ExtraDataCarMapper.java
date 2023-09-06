package org.kamenchuk.mapper;

import org.kamenchuk.dto.extraDataCarDTO.ExtraDataCarCreateRequest;
import org.kamenchuk.models.ExtraDataCar;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExtraDataCarMapper {
    ExtraDataCar toModel (ExtraDataCarCreateRequest dto);
}
