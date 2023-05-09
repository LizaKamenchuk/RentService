package org.kamenchuk.rentModule.controllers;

import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.rentModule.feinClient.FeignExtraDataClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest_module/extraData")
public class ExtraDataController {
    private final FeignExtraDataClient feignExtraDataClient;

    public ExtraDataController(FeignExtraDataClient feignExtraDataClient) {
        this.feignExtraDataClient = feignExtraDataClient;
    }

    @GetMapping("/getById/{id}")
    public ExtraUserDataUpdateRequest getById(@PathVariable Long id)  {
        return feignExtraDataClient.getById(id);
    }

    @PatchMapping("/updateExtra")
    public ExtraUserDataUpdateRequest update(@RequestBody ExtraUserDataUpdateRequest request, @RequestParam Long idED){
        return feignExtraDataClient.update(request,idED);
    }

}
