package org.kamenchuk.mapper;

import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.models.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(source = "model", target = "model.model")
    @Mapping(source = "mark", target = "model.mark.mark")
    Car toCar(CarCreateRequest request);

    @Mapping(source = "model.model",target = "model")
    @Mapping(source = "model.mark.mark",target = "mark")
    CarResponse toDto(Car car);

    @Mapping(source = "model", target = "model.model")
    @Mapping(source = "mark", target = "model.mark.mark")
    Car toCar(CarResponse request);

    @Mapping(source = "model", target = "model.model")
    @Mapping(source = "mark", target = "model.mark.mark")
    Car toCar(CarUpdateRequest request);
}
