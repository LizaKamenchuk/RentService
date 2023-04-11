package org.kamenchuk.feignClient;

import org.kamenchuk.dto.carDTO.PhotoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "carPhotoClient", url = "http://localhost:8081/photo")
public interface CarPhotoClient {
    @GetMapping(value="/getPhotos/{idCar}",produces = "application/json")
    List<PhotoResponse> getPhotos(@PathVariable Integer idCar);
}
