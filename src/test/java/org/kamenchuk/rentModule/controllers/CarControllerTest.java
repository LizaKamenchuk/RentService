package org.kamenchuk.rentModule.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.rentModule.feinClient.FeignCarClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CarController.class})
class CarControllerTest {
    @Autowired
    private CarController controller;

    @MockBean
    private FeignCarClient feignCarClient;

    @Test
    void getAll() {
        List<CarResponse> responses = new ArrayList<>();
        when(feignCarClient.getAll()).thenReturn(responses);
        List<CarResponse> results = controller.getAll();
        assertAll(()->{
            assertNotNull(results);
            assertEquals(results,responses);
        });
    }

    @Test
    void getCarById() {
        Integer idCar = 1;
        CarResponse response = CarResponse.builder()
                .id(1)
                .build();
        when(feignCarClient.getCarById(idCar)).thenReturn(response);
        CarResponse result = controller.getCarById(idCar);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void getByCarNumber() {
        String carNumber = "1111KK";
        CarResponse response = CarResponse.builder()
                .id(1)
                .carNumber(carNumber)
                .build();
        when(feignCarClient.getByCarNumber(carNumber)).thenReturn(response);
        CarResponse result = controller.getByCarNumber(carNumber);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void create() {
        MultipartFile file = null;
        CarCreateRequest request = new CarCreateRequest();
        CarResponse response = new CarResponse();
        when(feignCarClient.create(file,request)).thenReturn(response);
        CarResponse result = controller.create(file,request);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void update() {
        CarUpdateRequest request = new CarUpdateRequest();
        Integer idCar =1;
        CarResponse response = new CarResponse();
        when(feignCarClient.update(request,idCar)).thenReturn(response);
        CarResponse result = controller.update(request,idCar);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }
}