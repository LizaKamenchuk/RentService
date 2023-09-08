package org.kamenchuk.controller;

import org.kamenchuk.dto.extraDataCarDTO.ExtraDataCarUpdateRequest;
import org.kamenchuk.models.ExtraDataCar;
import org.kamenchuk.service.ExtraDataCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent_module/extra_data_car")
public class ExtraDataCarController {

    private final ExtraDataCarService extraDataCarService;

    @Autowired
    public ExtraDataCarController(ExtraDataCarService extraDataCarService) {
        this.extraDataCarService = extraDataCarService;
    }

    @PostMapping("/save")
    public ExtraDataCar save(@RequestBody ExtraDataCar extraDataCar){
        return extraDataCarService.save(extraDataCar);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        extraDataCarService.delete(id);
    }

    @GetMapping("/get/{id}")
    public ExtraDataCar getById(@PathVariable Integer id){
        return extraDataCarService.getById(id);
    }

    @PostMapping("/update/{id}")
    public ExtraDataCar update(@PathVariable Integer id, @RequestBody ExtraDataCarUpdateRequest updateRequest){
        return extraDataCarService.update(id,updateRequest);
    }

}
