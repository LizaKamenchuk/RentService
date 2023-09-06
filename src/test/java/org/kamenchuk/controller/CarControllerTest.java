package org.kamenchuk.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.dto.carDTO.PhotoResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.feignClient.FeignCarPhotoClient;
import org.kamenchuk.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CarController.class})
class CarControllerTest {
    @Autowired
    private CarController controller;
    @MockBean
    private CarServiceImpl service;
    @MockBean
    private FeignCarPhotoClient feignCarPhotoClient;

    @Test
    void getAll() {
        List<CarResponse> responseList = new ArrayList<>();
        when(service.getAll()).thenReturn(responseList);
        List<CarResponse> resultList = controller.getAll();
        assertEquals(resultList,responseList);
    }

    @Test
    void getCarById() throws UpdatingException {
        Integer idCar = 1;
        List<PhotoResponse> photos = new ArrayList<>();
        CarResponse response = new CarResponse();
        when(feignCarPhotoClient.getPhotos(idCar)).thenReturn(photos);
        when(service.getCarById(idCar,photos)).thenReturn(response);
        CarResponse result = controller.getCarById(idCar);
        assertEquals(result,response);

    }

    @Test
    void getByCarNumber() throws ResourceNotFoundException {
        String carNumber = "1515FD";
        CarResponse response = new CarResponse();
        when(service.getCarByNumber(carNumber)).thenReturn(response);
        CarResponse result = controller.getByCarNumber(carNumber);
        assertEquals(result,response);
    }

    @Test
    void create() throws CreationException {
        MultipartFile file = null;
        CarCreateRequest request = new CarCreateRequest();
        CarResponse response = new CarResponse();
        when(service.create(request)).thenReturn(response);
        CarResponse result = controller.create(request);
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
        Integer idCar =1;
        CarUpdateRequest request = new CarUpdateRequest();
        CarResponse response = new CarResponse();
        when(service.update(request,idCar)).thenReturn(response);
        CarResponse result = controller.update(request,idCar);
        assertEquals(result,response);
    }
}