package org.kamenchuk.rentModule.feinClient;

import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "feignExtraDataClient", url = "http://localhost:8080/rent_module/extraData")
public interface FeignExtraDataClient {
    @GetMapping("/getById/{id}")
    ExtraUserDataUpdateRequest getById(@PathVariable Long id);

    @PatchMapping("/updateExtra")
    ExtraUserDataUpdateRequest update(@RequestBody ExtraUserDataUpdateRequest request, @RequestParam Long idED);
}
