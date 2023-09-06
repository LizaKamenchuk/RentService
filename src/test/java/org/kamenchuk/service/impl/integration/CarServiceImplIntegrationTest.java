package org.kamenchuk.service.impl.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.dto.carDTO.PhotoResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarServiceImplIntegrationTest extends PostgresMongoContainer{
    @Autowired
    private CarService carService;


    @Test
    @Order(2)
    void getAll() {
        List<CarResponse> results = carService.getAll();
        assertEquals(results.size(),1);
    }

    @Order(1)
    @Test
    void create() throws CreationException {
        MultipartFile file = new MockMultipartFile("init_db.sql","Hello word!".getBytes());
        CarCreateRequest request = CarCreateRequest.builder()
                .price(200)
                .carNumber("1111HH")
                .model("X5")
                .mark("BMW")
                .build();
        CarResponse result = carService.create(request);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result.getCarNumber(),request.getCarNumber());
        });
    }

    @Test
    @Order(3)
    void getCarByNumber() throws ResourceNotFoundException {
        String carNumber = "1111HH";
        CarResponse result = carService.getCarByNumber(carNumber);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result.getCarNumber(),carNumber);
        });
    }

    @Order(6)
    @Test
    void deleteById() {
        Integer idCar = 1;
        carService.deleteById(idCar);
        assertThrows(ResourceNotFoundException.class, () -> carService.getCarByNumber("1111HH"));
    }

    @Order(7)
    @Test
    void deleteByCarNumber() throws CreationException {
        MultipartFile file = new MockMultipartFile("init_db.sql","Hello word!".getBytes());
        CarCreateRequest request = CarCreateRequest.builder()
                .price(200)
                .carNumber("1111HH")
                .model("X5")
                .mark("BMW")
                .build();
        CarResponse result = carService.create(request);
        carService.deleteByCarNumber(result.getCarNumber());
        assertThrows(ResourceNotFoundException.class, () -> carService.getCarByNumber(result.getCarNumber()));
    }

    @Order(4)
    @Test
    void update() throws UpdatingException {
        Integer idCar = 1;
        CarUpdateRequest request = CarUpdateRequest.builder()
                .carNumber("1212KK")
                .price(300)
                .build();
        CarResponse result = carService.update(request, idCar);
        assertEquals(result.getCarNumber(),request.getCarNumber());
        assertEquals(result.getPrice(),request.getPrice());
    }

    @Order(5)
    @Test
    void getCarById() throws UpdatingException {
        Integer idCar = 1;
        List<PhotoResponse> photos = null;
        CarResponse result = carService.getCarById(idCar,photos);
        assertEquals(result.getCarNumber(),"1212KK");
    }
}