package org.kamenchuk.service.impl;

import org.kamenchuk.dao.CarDao;
import org.kamenchuk.dao.MarkDao;
import org.kamenchuk.dao.ModelDao;
import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.mapper.CarMapper;
import org.kamenchuk.models.Car;
import org.kamenchuk.models.Mark;
import org.kamenchuk.models.Model;
import org.kamenchuk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CarResponse getCarByNumber(String carNumber) {
        if (carNumber == null) return null;
        return carDao.getCarByCarNumber(carNumber)
                .map(carMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Car with this number isn`t exist"));
    }

    @Override
    public void deleteById(Integer idCar) {
        carDao.deleteById(idCar);
    }

    @Override
    public void deleteByCarNumber(String carNumber) {
        carDao.deleteCarByCarNumber(carNumber);
    }

    @Override
    public CarResponse update(CarResponse request) {
        return Optional.ofNullable(request)
                .map(carMapper::toCar)
                .map(car -> setUpdates(request,car))
                .map(car -> setModelForCreate(request.getModel(),request.getMark(), car))
                .map(carDao::save)
                .map(carMapper::toDto)
                .orElseThrow(()-> new RuntimeException("Car isn`t updated"));
    }

    private Car setModelForCreate(String modelS,String markS, Car car) {
        Mark mark = Mark.builder()
                .mark(markS)
                .build();
        Model model = Model.builder()
                .model(modelS)
                .mark(mark)
                .build();
        if (!modelDao.existsModelByModelAndMark_Mark(modelS, markS)) {
            mark = markDao.existsMarkByMark(markS)
                    ? markDao.findMarkByMark(markS).get() : markDao.save(new Mark());
            model.setMark(mark);
            modelDao.save(model);
        } else {
            model = modelDao.findModelByModelAndMark_Mark(modelS,markS).get();
        }
        car.setModel(model);
        return car;
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
