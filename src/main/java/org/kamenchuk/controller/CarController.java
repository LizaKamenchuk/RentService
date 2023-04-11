package org.kamenchuk.controller;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.dto.carDTO.PhotoResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.feignClient.CarPhotoClient;
import org.kamenchuk.service.CarService;
import org.kamenchuk.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {
    private final CarService carService;

    private final CarPhotoClient carPhotoClient;


    @Autowired
    CarController(CarServiceImpl carService,
                  CarPhotoClient carPhotoClient) {
        this.carService = carService;
        this.carPhotoClient = carPhotoClient;
    }

    @GetMapping(value = "/CarPhotoWebClient", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public static Flux<PhotoResponse> getPhotos(Integer idCar) {
        Flux<PhotoResponse> photoFlux = WebClient.create()
                .get()
                .uri("http://localhost:8081/photo/getPhotos/" + idCar.toString())
                .retrieve()
                .bodyToFlux(PhotoResponse.class);
        photoFlux.subscribe(photo -> log.info(photo.toString()));
        return photoFlux;
    }

    @GetMapping(value = "/getAll")
    public List<CarResponse> getAll() {
        return carService.getAll();
    }

    @GetMapping(value = "getCarById/{idCar}")
    public CarResponse getCarById(@PathVariable Integer idCar) throws UpdatingException {
        List<PhotoResponse> photos = carPhotoClient.getPhotos(idCar);
        System.out.println("Feign: ");
        photos.forEach(System.out::println);
        System.out.println("WebClient: ");
        Flux<PhotoResponse> flux = getPhotos(idCar);
        List<PhotoResponse> photos2 = flux.toStream().toList();
        return carService.getCarById(idCar,photos);
    }

    @GetMapping(value = "/getByCarNumber/{carNumber}")
    public CarResponse getByCarNumber(@PathVariable String carNumber) throws ResourceNotFoundException {
        return carService.getCarByNumber(carNumber);
    }

    @PostMapping(value = "/create"/*, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}*/)
    public CarResponse create(@RequestPart MultipartFile file, @RequestPart CarCreateRequest request) throws CreationException {
        return carService.create(request, file);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public void deleteById(@PathVariable Integer id) {
        carService.deleteById(id);
    }

    @DeleteMapping(value = "/deleteByCarNumber/{carNumber}")
    public void deleteByCarNumber(@PathVariable String carNumber) {
        carService.deleteByCarNumber(carNumber);
    }

    @PatchMapping(value = "/update")
    public CarResponse update(@Valid @RequestBody CarUpdateRequest request, @RequestParam Integer idCar) throws UpdatingException {
        return carService.update(request, idCar);
    }
}
