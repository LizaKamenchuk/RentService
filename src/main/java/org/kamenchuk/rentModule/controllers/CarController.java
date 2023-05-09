package org.kamenchuk.rentModule.controllers;

import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.kamenchuk.rentModule.feinClient.FeignCarClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rent_module/car")
public class CarController {
    private final FeignCarClient feignCarClient;

    @Autowired
    public CarController(FeignCarClient feignCarClient) {
        this.feignCarClient = feignCarClient;
    }

    @GetMapping(value = "/admin/getAll")
    public List<CarResponse> getAll() {
        return feignCarClient.getAll();
    }

    @GetMapping(value = "/getCarById/{idCar}")
    public CarResponse getCarById(@PathVariable Integer idCar)  {
        return feignCarClient.getCarById(idCar);
    }

    @GetMapping(value = "/getByCarNumber/{carNumber}")
    public CarResponse getByCarNumber(@PathVariable String carNumber) {
        return feignCarClient.getByCarNumber(carNumber);
    }

    @PostMapping(value = "/admin/create"/*, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}*/)
    public CarResponse create(@RequestPart MultipartFile file, @RequestPart CarCreateRequest request) {
        return feignCarClient.create(file,request);
    }

    @DeleteMapping(value = "/admin/deleteById/{id}")
    public void deleteById(@PathVariable Integer id) {
        feignCarClient.deleteById(id);
    }

    @DeleteMapping(value = "/admin/deleteByCarNumber/{carNumber}")
    public void deleteByCarNumber(@PathVariable String carNumber) {
        feignCarClient.deleteByCarNumber(carNumber);
    }

    @PatchMapping(value = "/admin/update")
    public CarResponse update(@Valid @RequestBody CarUpdateRequest request, @RequestParam Integer idCar){
        return feignCarClient.update(request, idCar);
    }
}
