package org.kamenchuk.controller;

import org.kamenchuk.models.Fuel;
import org.kamenchuk.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent_module/fuel")
public class FuelController {

    private final FuelService fuelService;

    @Autowired
    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @PostMapping("/save")
    public String save(@RequestParam String fuelType){
        return fuelService.save(fuelType);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        fuelService.deleteById(id);
    }

    @GetMapping("/get/{fuelType}")
    public Fuel findByFuelType(@PathVariable String fuelType){
        return fuelService.findByFuelType(fuelType);
    }
}
