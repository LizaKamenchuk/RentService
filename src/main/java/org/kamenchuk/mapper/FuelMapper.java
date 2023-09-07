package org.kamenchuk.mapper;

import org.kamenchuk.dto.carDTO.extraDataCarDTO.FuelDto;
import org.kamenchuk.models.Fuel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FuelMapper {
    FuelDto toDto(Fuel fuel);
    Fuel toModel(FuelDto dto);
}
