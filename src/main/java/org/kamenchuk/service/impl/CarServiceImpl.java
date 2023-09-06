package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.models.*;
import org.kamenchuk.repository.CarRepository;
import org.kamenchuk.repository.MarkRepository;
import org.kamenchuk.repository.ModelRepository;
import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.dto.carDTO.PhotoResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.feignClient.FeignCarPhotoClient;
import org.kamenchuk.mapper.CarMapper;
import org.kamenchuk.service.CarService;
import org.kamenchuk.service.ExtraDataCarService;
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
    //TODO create service
    private final ModelRepository modelRepository;
    //TODO create service
    private final MarkRepository markRepository;
    private final CarMapper carMapper;
    private final FeignCarPhotoClient feignCarPhotoClient;

    private final ExtraDataCarService extraDataCarService;

    @Autowired
    CarServiceImpl(CarRepository carRepository,
                   ModelRepository modelRepository,
                   MarkRepository markRepository,
                   CarMapper carMapper,
                   FeignCarPhotoClient feignCarPhotoClient,
                   ExtraDataCarService extraDataCarService) {
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
        this.markRepository = markRepository;
        this.carMapper = carMapper;
        this.feignCarPhotoClient = feignCarPhotoClient;
        this.extraDataCarService = extraDataCarService;
    }


    @Override
    @Transactional
    public List<CarResponse> getAll() {
        return carRepository.findAll().stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public CarResponse create(CarCreateRequest request) throws CreationException {
        extraDataCarService.save(ExtraDataCar.builder()
                .fuel_consumption(request.getExtraDataCarCreateRequest().getFuel_consumption())
                .limitations(request.getExtraDataCarCreateRequest().getLimitations())
                .manufacture_year(request.getExtraDataCarCreateRequest().getManufacture_year())
                .fuel(Fuel.builder().fuelType(request.getFuelType()).build())
                .carClass(CarClass.builder().classType(request.getCarClassType()).build())
                .transmission(Transmission.builder().transmissionType(request.getTransmissionType()).build())
                .build());
        return Optional.of(request)
                .map(carMapper::toCar)
                .map(car -> setModel(setModelForCreate(request.getModel(), request.getMark(), car), car))
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
        if (carNumber == null) {
            return null;
        }
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
    public CarResponse getCarById(Integer idCar, List<PhotoResponse> photos) throws UpdatingException {
        if (carRepository.findById(idCar).isPresent()) {
            Car car = carRepository.findById(idCar).get();
            CarResponse response = carMapper.toDto(car);
            response.setPhotos(photos);
            return response;
        } else throw new ResourceNotFoundException("Car with this id does not exist");
    }

    private Car setModel(Model model, Car car) {
        car.setModel(model);
        return car;
    }

    private Model setModelForCreate(String modelName, String markName, Car car) {
        Mark markNew = Mark.builder()
                .mark(markName)
                .build();
        Model model = Model.builder()
                .model(modelName)
                .build();
        if ((modelName == null || modelName.isEmpty()) || (markName == null || markName.isEmpty())) {
            model = car.getModel();
            markNew = model.getMark();
            model.setMark(markNew);
        } else {
            if (!modelRepository.existsModelByModelAndMark_Mark(modelName, markName)) {
                Mark mark = markRepository.existsMarkByMark(markName) ?
                        markRepository.findMarkByMark(markName).get() : markRepository.save(markNew);
                model.setMark(mark);
                modelRepository.save(model);
            } else {
                model = modelRepository.findModelByModelAndMark_Mark(modelName, markName).get();
            }
        }
        return model;
    }

    private Car setUpdates(CarUpdateRequest request, Car car) {
        return Car.builder()
                .id(car.getId())
                .model(setModelForCreate(request.getModel(), request.getMark(), car))
                .carNumber((request.getCarNumber() == null || request.getCarNumber().isEmpty()) ? car.getCarNumber() : request.getCarNumber())
                .price(request.getPrice() == null ? car.getPrice() : request.getPrice())
                .build();
    }

}
