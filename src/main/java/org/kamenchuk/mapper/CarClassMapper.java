package org.kamenchuk.mapper;

import org.kamenchuk.dto.carDTO.extraDataCarDTO.CarClassDto;
import org.kamenchuk.models.CarClass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarClassMapper {
    CarClassDto toDto(CarClass carClass);
    CarClass toModel(CarClassDto dto);
}
