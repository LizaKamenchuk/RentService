package org.kamenchuk.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.PhotoResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.kafka.KafkaCarProducer;
import org.kamenchuk.mapper.CarMapper;
import org.kamenchuk.models.Car;
import org.kamenchuk.models.Mark;
import org.kamenchuk.models.Model;
import org.kamenchuk.repository.CarRepository;
import org.kamenchuk.repository.MarkRepository;
import org.kamenchuk.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CarServiceImpl.class})
class CarServiceImplTest {
    @Autowired
    private CarServiceImpl carService;
    @MockBean
    private CarRepository carRepository;
    @MockBean
    private ModelRepository modelRepository;
    @MockBean
    private MarkRepository markRepository;
    @MockBean
    private  CarMapper carMapper;
    @MockBean
    private  KafkaCarProducer producer;

    @Test
    void getAll() {
        List<Car> carList = new ArrayList<>();
        Car car = new Car();
        CarResponse response = new CarResponse();
        List<CarResponse> responseList = new ArrayList<>();
        when(carRepository.findAll()).thenReturn(carList);
        when(carMapper.toDto(car)).thenReturn(response);
        List<CarResponse> resultList = carService.getAll();
        assertEquals(resultList,responseList);
    }

    @Test
    void create() throws CreationException {
        CarCreateRequest request = CarCreateRequest.builder()
                .carNumber("1111MM")
                .mark("mark")
                .model("model")
                .price(200)
                .build();
        Car car = Car.builder()
                .carNumber("1111MM")
                .model(new Model(1,"model",new Mark(1,"mark")))
                .price(200)
                .build();
        CarResponse response = new CarResponse();
        when(carMapper.toCar(request)).thenReturn(car);
        when(carRepository.save(car)).thenReturn(car);
        when(carMapper.toDto(car)).thenReturn(response);
        CarResponse result = carService.create(request);
        assertEquals(result,response);
    }

    @Test
    void getCarByNumber() throws ResourceNotFoundException {
        String carNumber = "1515FD";
        assertNotNull(carNumber);
        Car car = new Car();
        CarResponse response = new CarResponse();
        when(carRepository.getCarByCarNumber(carNumber)).thenReturn(Optional.of(car));
        when(carMapper.toDto(car)).thenReturn(response);
        CarResponse result = carService.getCarByNumber(carNumber);
        assertEquals(result,response);
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteByCarNumber() {
    }

    @Test
    void update() throws UpdatingException {
//        Integer idCar = 1;
//        CarUpdateRequest request = CarUpdateRequest.builder()
//                .carNumber("1111MM")
//                .mark("mark")
//                .model("model")
//                .limitations(null)
//                .price(200)
//                .build();
//        Car car = Car.builder()
//                .id(idCar)
//                .carNumber("1111MM")
//                .model(new Model(1,"model",new Mark(1,"mark")))
//                .limitations(null)
//                .price(200)
//                .build();
//        CarResponse response = CarResponse.builder()
//                .id(idCar)
//                .carNumber("1111MM")
//                .mark("mark")
//                .model("model")
//                .limitations(null)
//                .price(200)
//                .build();
//        when(carDao.findById(idCar)).thenReturn(Optional.ofNullable(car));
//        when(carDao.save(car)).thenReturn(car);
//        when(carMapper.toDto(car)).thenReturn(response);
//        CarResponse result = carService.update(request,idCar);
//        assertAll(()->{
//            assertNotNull(result);
//            assertEquals(result,response);
//        });
    }

    @Test
    void getCarById() throws UpdatingException {
        Integer idCar = 1;
        List<PhotoResponse> photos = null;
        Car car = new Car();
        CarResponse response = new CarResponse();
        when(carRepository.findById(idCar)).thenReturn(Optional.of(car));
        when(carMapper.toDto(car)).thenReturn(response);
        response.setPhotos(photos);
        CarResponse result = carService.getCarById(idCar,photos);
        assertEquals(result,response);
    }
}