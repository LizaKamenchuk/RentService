package org.kamenchuk.controller;

import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.service.CarService;
import org.kamenchuk.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/car")
@EnableKafka
public class CarController {
    private final CarService carService;


    @Autowired
    CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/getAll")
    public List<CarResponse> getAll(){
        return carService.getAll();
    }

    @GetMapping(value = "/getByCarNumber/{carNumber}")
    public CarResponse getByCarNumber(@PathVariable String carNumber) throws ResourceNotFoundException {
        return carService.getCarByNumber(carNumber);
    }

    @PostMapping (value = "/create"/*, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}*/)
    public CarResponse create(@RequestPart MultipartFile file, @RequestPart CarCreateRequest request) throws CreationException {
        return carService.create(request,file);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public void deleteById(@PathVariable Integer id){
        carService.deleteById(id);
    }

    @DeleteMapping(value = "/deleteByCarNumber/{carNumber}")
    public void deleteByCarNumber(@PathVariable String carNumber){
        carService.deleteByCarNumber(carNumber);
    }

    @PatchMapping(value = "/update")
    public CarResponse update(@Valid @RequestBody CarUpdateRequest request, @RequestParam Integer idCar) throws UpdatingException {
        return carService.update(request,idCar);
    }
}
