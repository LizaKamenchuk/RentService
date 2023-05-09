package org.kamenchuk.controller;

import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.service.ExtraUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent_module/extraData")
public class ExtraUsersDataController {

    private final ExtraUsersService extraUsersService;

    @Autowired
    ExtraUsersDataController(ExtraUsersService extraUsersService){
        this.extraUsersService = extraUsersService;
    }

    @GetMapping("/getById/{id}")
    public ExtraUserDataUpdateRequest getById(@PathVariable Long id) throws ResourceNotFoundException {
        return extraUsersService.getExtraDataById(id);
    }

    @PatchMapping("/updateExtra")
    public ExtraUserDataUpdateRequest update(@RequestBody ExtraUserDataUpdateRequest request,@RequestParam Long idED){
        return extraUsersService.updateExtraData(request,idED);
    }

}
