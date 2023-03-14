package org.kamenchuk.controller;

import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.service.CarService;
import org.kamenchuk.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carController")
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
    public CarResponse getByCarNumber(@PathVariable String carNumber){
        return carService.getCarByNumber(carNumber);
    }

    @PostMapping (value = "/create")
    public CarResponse create(@RequestBody CarCreateRequest request){
        return carService.create(request);
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
    public CarResponse update(CarResponse request){
        return carService.update(request);
    }
}
