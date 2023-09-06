package org.kamenchuk.controller;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.dto.carDTO.PhotoResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.feignClient.FeignCarPhotoClient;
import org.kamenchuk.service.CarService;
import org.kamenchuk.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Validated
@RestController
@RequestMapping("/rent_module/car")
@Slf4j
public class CarController {
    private final CarService carService;

    private final FeignCarPhotoClient feignCarPhotoClient;


    @Autowired
    CarController(CarServiceImpl carService,
                  FeignCarPhotoClient feignCarPhotoClient) {
        this.carService = carService;
        this.feignCarPhotoClient = feignCarPhotoClient;
    }

//    @GetMapping(value = "/CarPhotoWebClient", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public static Flux<PhotoResponse> getPhotos(Integer idCar) {
//        Flux<PhotoResponse> photoFlux = WebClient.create()
//                .get()
//                .uri("http://localhost:8081/photo/getPhotos/" + idCar.toString())
//                .retrieve()
//                .bodyToFlux(PhotoResponse.class);
//        photoFlux.subscribe(photo -> log.info(photo.toString()));
//        return photoFlux;
//    }

    @GetMapping(value = "/getAll")
    public List<CarResponse> getAll() {
        return carService.getAll();
    }

    @GetMapping(value = "getCarById/{idCar}")
    public CarResponse getCarById(@PathVariable Integer idCar) throws UpdatingException {
        List<PhotoResponse> photos = feignCarPhotoClient.getPhotos(idCar);

        System.out.println("Feign: ");
        photos.forEach(System.out::println);

        return carService.getCarById(idCar,photos);
    }

    @GetMapping(value = "/getByCarNumber/{carNumber}")
    public CarResponse getByCarNumber(@PathVariable String carNumber) throws ResourceNotFoundException {
        return carService.getCarByNumber(carNumber);
    }

    @PostMapping(value = "/admin/create")
    public CarResponse create(@RequestBody CarCreateRequest request) throws CreationException {
        return carService.create(request);
    }

    @DeleteMapping(value = "/admin/deleteById/{id}")
    public void deleteById(@PathVariable Integer id) {
        carService.deleteById(id);
    }

    @DeleteMapping(value = "/admin/deleteByCarNumber/{carNumber}")
    public void deleteByCarNumber(@PathVariable String carNumber) {
        carService.deleteByCarNumber(carNumber);
    }

    @PatchMapping(value = "/admin/update")
    public CarResponse update(@Valid @RequestBody CarUpdateRequest request, @RequestParam Integer idCar) throws UpdatingException {
        return carService.update(request, idCar);
    }
}
