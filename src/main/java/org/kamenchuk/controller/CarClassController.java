package org.kamenchuk.controller;

import org.kamenchuk.models.CarClass;
import org.kamenchuk.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent_module/car_class")
public class CarClassController {
    private final CarClassService carClassService;

    @Autowired
    public CarClassController(CarClassService carClassService) {
        this.carClassService = carClassService;
    }

    @PostMapping("/save")
    public String save(@RequestParam String carClassType){
        return carClassService.save(carClassType);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id){
        carClassService.deleteById(id);
    }

    @GetMapping("/get/{carClassType}")
    public CarClass findByCarClassType(@PathVariable String carClassType){
        return carClassService.findByCarClassType(carClassType);
    }

}
