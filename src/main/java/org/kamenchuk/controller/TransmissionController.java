package org.kamenchuk.controller;

import org.kamenchuk.dto.carDTO.extraDataCarDTO.TransmissionDto;
import org.kamenchuk.models.Transmission;
import org.kamenchuk.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rent_module/transmission")
public class TransmissionController {

    private final TransmissionService transmissionService;

    @Autowired
    public TransmissionController(TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }

    @PostMapping("/save")
    public TransmissionDto save(@RequestParam TransmissionDto transmissionType) {
        return transmissionService.save(transmissionType);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        transmissionService.deleteById(id);
    }

    @GetMapping("/get/{transmissionType}")
    public Transmission findByTransmissionType(@PathVariable String transmissionType) {
        return transmissionService.findByTransmissionTypeOrFail(transmissionType);
    }
}
