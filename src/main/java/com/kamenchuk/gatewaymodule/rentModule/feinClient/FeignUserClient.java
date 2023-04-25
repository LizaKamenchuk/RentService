package com.kamenchuk.gatewaymodule.rentModule.feinClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "feignUserClient", url = "http://localhost:8080/rent_module/user")
public interface FeignUserClient {
    @GetMapping(value = "/admin/all")
    void getAllUser();
}
