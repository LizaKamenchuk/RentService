package com.kamenchuk.controller;

import com.kamenchuk.dto.PhotoResponse;
import com.kamenchuk.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService service;

    @Autowired
    PhotoController(PhotoService service) {
        this.service = service;
    }

    @PostMapping("/add/{file}/{idCar}")
    public String  addPhoto(@PathVariable MultipartFile file, @PathVariable Integer idCar) throws IOException {
        return service.addPhoto(file,idCar);
    }

    @DeleteMapping("/delete/{idPhoto}")
    public void deletePhotoById(@PathVariable String idPhoto){
        service.deletePhotoById(idPhoto);
    }

    @GetMapping("/get/{idCar}")
    public List<PhotoResponse> getPhoto(@PathVariable Integer idCar){
      List<PhotoResponse> photos= service.getPhoto(idCar);
      photos.forEach(System.out::println);
      return photos;
    }
}
