package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dao.CarDao;
import org.kamenchuk.dao.MarkDao;
import org.kamenchuk.dao.ModelDao;
import org.kamenchuk.dto.carDTO.*;
import org.kamenchuk.dto.mapper.CarMapper;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.kafka.KafkaCarProducer;
import org.kamenchuk.models.Car;
import org.kamenchuk.models.Mark;
import org.kamenchuk.models.Model;
import org.kamenchuk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
public class CarServiceImpl implements CarService{
    private final CarDao carDao;
    private final ModelDao modelDao;
    private final MarkDao markDao;
    private final CarMapper carMapper;
    private final KafkaCarProducer producer;

    @Autowired
    CarServiceImpl(CarDao carDao,
                   ModelDao modelDao,
                   MarkDao markDao,
                   CarMapper carMapper, KafkaCarProducer producer) {
        this.carDao = carDao;
        this.modelDao = modelDao;
        this.markDao = markDao;
        this.carMapper = carMapper;
        this.producer = producer;
    }


    @Override
    @Transactional
    public List<CarResponse> getAll() {
        return carDao.findAll().stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CarResponse create(CarCreateRequest request, MultipartFile file) throws CreationException {

        return Optional.ofNullable(request)
                .map(carMapper::toCar)
                .map(car -> setModel(setModelForCreate(request.getModel(), request.getMark(), car), car))
                .map(carDao::save)
                .map(carMapper::toDto)
                .map(carRes->{ producer.sendGetPhotoTopic(toPhotoDto(carRes.getId(), file));
                return carRes;})
                .orElseThrow(() -> {
                    log.error("create(). Can not create car!");
                    return new CreationException("Car isn`t created");
                });
    }

    private PhotoDto toPhotoDto(Integer idCar,MultipartFile file){
        try{ PhotoDto photo = PhotoDto.builder()
                .idCar(idCar)
                .fileName(file.getName())
                .fileBytes(file.getBytes())
                .build();
            return photo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
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

    @Override
    public CarResponse getCarById(Integer idCar, List<PhotoResponse> photos) throws UpdatingException {
        Car car = carDao.findById(idCar).get();
        CarResponse response = carMapper.toDto(car);
        response.setPhotos(photos);
        return response;
//        return carDao.findById(idCar)
//                .map(carMapper::toDto)
//                .orElseThrow(() -> {
//                    log.error("update(). Car isn`t updated");
//                    return new UpdatingException("Car isn`t updated");
//                });
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
        if (modelName.isEmpty() || markName.isEmpty()) {
            model = car.getModel();
            markNew = model.getMark();
            model.setMark(markNew);
        } else {
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

    private Car setUpdates(
            CarUpdateRequest request,
            Car car
    ) {
        return Car.builder()
                .id(car.getId())
                .model(setModelForCreate(request.getModel(), request.getMark(), car))
                .carNumber(request.getCarNumber().isEmpty() ? car.getCarNumber() : request.getCarNumber())
                .price(request.getPrice() == null ? car.getPrice() : request.getPrice())
                .limitations(request.getLimitations().isEmpty() ? car.getLimitations() : request.getLimitations())
                .build();
    }


}
