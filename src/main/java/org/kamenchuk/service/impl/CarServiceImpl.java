package org.kamenchuk.service.impl;

import org.kamenchuk.dao.CarDao;
import org.kamenchuk.dao.MarkDao;
import org.kamenchuk.dao.ModelDao;
import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.dto.mapper.CarMapper;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.models.Car;
import org.kamenchuk.models.Mark;
import org.kamenchuk.models.Model;
import org.kamenchuk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarDao carDao;
    private final ModelDao modelDao;
    private final MarkDao markDao;
    private final CarMapper carMapper;

    @Autowired
    CarServiceImpl(CarDao carDao,
                   ModelDao modelDao,
                   MarkDao markDao,
                   CarMapper carMapper) {
        this.carDao = carDao;
        this.modelDao = modelDao;
        this.markDao = markDao;
        this.carMapper = carMapper;
    }

    @Override
    @Transactional
    public List<CarResponse> getAll() {
        return carDao.findAll().stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarResponse create(CarCreateRequest request) {
        return Optional.ofNullable(request)
                .map(carMapper::toCar)
                .map(car -> setModelForCreate(request.getModel(),request.getMark(), car))
                .map(carDao::save)
                .map(carMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Car is not created"));
    }

    @Override
    @Transactional
    public CarResponse getCarByNumber(String carNumber) throws ResourceNotFoundException {
        if (carNumber == null) {
            return null;
        }
        return carDao.getCarByCarNumber(carNumber)
                .map(carMapper::toDto)
                .orElseThrow(() -> {
                    log.error("getCarByNumber(). Car with this number isn`t exist");
                    return new ResourceNotFoundException("Car with this number isn`t exist");
                });
    }

    @Override
    @Transactional
    public void deleteById(Integer idCar) {
        carDao.deleteById(idCar);
    }

    @Override
    @Transactional
    public void deleteByCarNumber(String carNumber) {
        carDao.deleteCarByCarNumber(carNumber);
    }

    @Override
    @Transactional
    public CarResponse update(CarUpdateRequest request, Integer idCar) throws UpdatingException {
        return carDao.findById(idCar)
                .map(car -> setUpdates(request, car))
                .map(carDao::save)
                .map(carMapper::toDto)
                .orElseThrow(() -> {
                    log.error("update(). Car isn`t updated");
                    return new UpdatingException("Car isn`t updated");
                });
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
        if (modelName.isEmpty() || markName.isEmpty()){
            model = car.getModel();
            markNew = model.getMark();
            model.setMark(markNew);
        }else {
            if (!modelDao.existsModelByModelAndMark_Mark(modelName, markName)) {
                Mark mark = markDao.existsMarkByMark(markName)
                        ? markDao.findMarkByMark(markName).get() : markDao.save(markNew);
                model.setMark(mark);
                modelDao.save(model);
            } else {
                model = modelDao.findModelByModelAndMark_Mark(modelName, markName).get();
            }
        }
        return model;
    }

    private Car setUpdates(CarResponse request, Car car) {
        if (request.getMark() != null) car.getModel().getMark().setMark(request.getMark());
        if (request.getModel() != null) car.getModel().setModel(request.getModel());
        if (request.getCarNumber() != null) car.setCarNumber(request.getCarNumber());
        if(request.getLimitations() != null) car.setLimitations(request.getLimitations());
        if(request.getPrice() != null) car.setPrice(request.getPrice());
        if(request.getIdImage() != null) car.setIdImage(request.getIdImage());
        return car;
    }
}
