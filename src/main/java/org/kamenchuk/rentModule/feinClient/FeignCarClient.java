package org.kamenchuk.rentModule.feinClient;

import org.kamenchuk.dto.carDTO.CarCreateRequest;
import org.kamenchuk.dto.carDTO.CarResponse;
import org.kamenchuk.dto.carDTO.CarUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "feignCarClient", url = "http://localhost:8080/rent_module/car")
public interface FeignCarClient {
    @GetMapping(value = "/getAll")
    List<CarResponse> getAll();

    @GetMapping(value = "getCarById/{idCar}")
    CarResponse getCarById(@PathVariable Integer idCar);

    @GetMapping(value = "/getByCarNumber/{carNumber}")
    CarResponse getByCarNumber(@PathVariable String carNumber);

    @PostMapping(value = "/admin/create"/*, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}*/)
    CarResponse create(@RequestPart MultipartFile file, @RequestPart CarCreateRequest request);

    @DeleteMapping(value = "/admin/deleteById/{id}")
    void deleteById(@PathVariable Integer id);

    @DeleteMapping(value = "/admin/deleteByCarNumber/{carNumber}")
    void deleteByCarNumber(@PathVariable String carNumber);

    @PatchMapping(value = "/admin/update")
    CarResponse update(@Valid @RequestBody CarUpdateRequest request, @RequestParam Integer idCar);
}
