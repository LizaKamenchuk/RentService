package com.kamenchuk.service;

import com.kamenchuk.dto.PhotoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    String addPhoto(MultipartFile file, Integer idCar) throws IOException;
    List<PhotoResponse> getPhoto(Integer idCar);

    void deletePhotoById(String idPhoto);

}
