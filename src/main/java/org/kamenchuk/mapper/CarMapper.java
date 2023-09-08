package org.kamenchuk.mapper;

import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarDocumentDto;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.models.Car;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface CarMapper {
    @Mapping(source = "model", target = "model.model")
    @Mapping(source = "mark", target = "model.mark.mark")
    @Mapping(source = "extraDataCarCreateRequest.fuel_consumption",target = "extraDataCar.fuel_consumption")
    @Mapping(source = "extraDataCarCreateRequest.limitations", target = "extraDataCar.limitations")
    @Mapping(source = "extraDataCarCreateRequest.manufacture_year",target = "extraDataCar.manufacture_year")
    @Mapping(source = "carClassType",target = "extraDataCar.carClass.classType")
    @Mapping(source = "fuelType",target = "extraDataCar.fuel.fuelType")
    @Mapping(source = "transmissionType",target = "extraDataCar.transmission.transmissionType")
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

    @Mapping(source = "extraDataCar.carClass.classType", target = "carClassType")
    @Mapping(source = "extraDataCar.fuel.fuelType",target = "fuelType")
    @Mapping(source = "extraDataCar.transmission.transmissionType",target = "transmissionType")
    @Mapping(source = "extraDataCar.limitations",target = "limitations")
    @Mapping(source = "extraDataCar.fuel_consumption",target = "fuel_consumption")
    @Mapping(source = "extraDataCar.manufacture_year",target = "manufacture_year")
    @Mapping(source = "model.model",target = "model")
    @Mapping(source = "model.mark.mark",target = "mark")
    CarDocumentDto toCarDocumentDto(Car car);
}
