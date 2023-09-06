package org.kamenchuk.controller;

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
    public String save(@RequestBody String transmissionType) {
        return transmissionService.save(transmissionType);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        transmissionService.deleteById(id);
    }

    @GetMapping("/get/{transmissionType}")
    public Transmission findByTransmissionType(@PathVariable String transmissionType) {
        return transmissionService.findByTransmissionType(transmissionType);
    }
}
