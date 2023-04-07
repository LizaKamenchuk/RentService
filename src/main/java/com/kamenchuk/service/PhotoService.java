package com.kamenchuk.service;

import com.kamenchuk.dto.PhotoDto;
import com.kamenchuk.dto.PhotoResponse;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    String addPhoto(PhotoDto photoDto) throws IOException;
    List<PhotoResponse> getPhoto(Integer idCar);

    void deletePhotoById(String idPhoto);

}
