package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarDocumentDto;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.dto.carDTO.model_markDTO.ModelCreateDto;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.feignClient.FeignCarPhotoClient;
import org.kamenchuk.mapper.CarMapper;
import org.kamenchuk.models.*;
import org.kamenchuk.repository.CarRepository;
import org.kamenchuk.service.CarService;
import org.kamenchuk.service.ExtraDataCarService;
import org.kamenchuk.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class implements CarService interface
 *
 * @author Liza Kamenchuk
 */
@Slf4j
@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelService modelService;
    private final CarMapper carMapper;
    private final FeignCarPhotoClient feignCarPhotoClient;
    private final ExtraDataCarService extraDataCarService;

    @Autowired
    CarServiceImpl(CarRepository carRepository,
                   ModelService modelService,
                   CarMapper carMapper,
                   FeignCarPhotoClient feignCarPhotoClient,
                   ExtraDataCarService extraDataCarService) {
        this.carRepository = carRepository;
        this.modelService = modelService;
        this.carMapper = carMapper;
        this.feignCarPhotoClient = feignCarPhotoClient;
        this.extraDataCarService = extraDataCarService;
    }


    @Override
    @Transactional(readOnly = true)
    public List<CarResponse> getAll() {
        return carRepository.findAll().stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CarResponse create(CarCreateRequest request) throws CreationException {
        ExtraDataCar extraDataCar = extraDataCarService.save(ExtraDataCar.builder()
                .fuel_consumption(request.getExtraDataCarCreateRequest().getFuel_consumption())
                .limitations(request.getExtraDataCarCreateRequest().getLimitations())
                .manufacture_year(request.getExtraDataCarCreateRequest().getManufacture_year())
                .fuel(Fuel.builder().fuelType(request.getFuelType()).build())
                .carClass(CarClass.builder().classType(request.getCarClassType()).build())
                .transmission(Transmission.builder().transmissionType(request.getTransmissionType()).build())
                .build());
        return Optional.of(request)
                .map(carMapper::toCar)
                .map(car -> {
                    car.setModel(modelService.saveIfNotExists(new ModelCreateDto(request.getModel(), request.getMark())));
                    return car;
                })
                .map(car -> {
                    car.setExtraDataCar(extraDataCar);
                    return car;
                })
                .map(carRepository::save)
                .map(carMapper::toDto)
                .orElseThrow(() -> {
                    log.error("create(). Can not create car!");
                    return new CreationException("Car isn`t created");
                });
    }


    @Override
    @Transactional(readOnly = true)
    public CarResponse getCarByNumber(String carNumber) throws ResourceNotFoundException {
        return carRepository.getCarByCarNumber(carNumber)
                .map(carMapper::toDto)
                .map(carResponse -> {
                    carResponse.setPhotos(feignCarPhotoClient.getPhotos(carResponse.getId()));
                    return carResponse;
                })
                .orElseThrow(() -> {
                    log.error("getCarByNumber(). Car with this number isn`t exist");
                    return new ResourceNotFoundException("Car with this number isn`t exist");
                });
    }

    @Override
    @Transactional
    public void deleteById(Integer idCar) {
        carRepository.deleteById(idCar);
    }

    @Override
    @Transactional
    public void deleteByCarNumber(String carNumber) {
        carRepository.deleteCarByCarNumber(carNumber);
    }

    @Override
    @Transactional
    public CarResponse update(CarUpdateRequest request, Integer idCar) throws UpdatingException {
        return carRepository.findById(idCar)
                .map(car -> setUpdates(request, car))
                .map(carRepository::save)
                .map(carMapper::toDto)
                .orElseThrow(() -> {
                    log.error("update(). Car isn`t updated");
                    return new UpdatingException("Car isn`t updated");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public CarResponse getCarById(Integer idCar) throws ResourceNotFoundException {
        return carRepository.findById(idCar)
                .map(carMapper::toDto)
                .map(carResponse -> {
                    try {
                        carResponse.setPhotos(feignCarPhotoClient.getPhotos(idCar));
                    } catch (Exception e) {
                        log.info("Problem in CarPhotoLoader Module");
                    }
                    return carResponse;
                }).orElseThrow(() -> {
                    throw new ResourceNotFoundException(String.format("Car with id %s does not exist", idCar));
                });
    }

    @Override
    public CarDocumentDto getCarByIdForDocument(Integer idCar) {
         return carRepository.findById(idCar)
                 .map(carMapper::toCarDocumentDto)
                 .orElseThrow(()->{
                     throw new ResourceNotFoundException(String.format("Car with id %s does not exist", idCar));
                 });
//       extraDataCarService.getById(car.get().getExtraDataCar().getId());


    }

    private Car setUpdates(CarUpdateRequest request, Car car) {
        return Car.builder()
                .id(car.getId())
                .model(modelService.saveIfNotExists(new ModelCreateDto(request.getModel(), request.getMark())))
                .carNumber((request.getCarNumber() == null || request.getCarNumber().isEmpty()) ? car.getCarNumber() : request.getCarNumber())
                .price(request.getPrice() == null ? car.getPrice() : request.getPrice())
                .build();
    }

}
