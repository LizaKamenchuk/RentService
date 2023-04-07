package com.kamenchuk.service;

import com.kamenchuk.dto.PhotoDto;
import com.kamenchuk.dto.PhotoMapper;
import com.kamenchuk.dto.PhotoResponse;
import com.kamenchuk.model.File;
import com.kamenchuk.model.Photo;
import com.kamenchuk.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository repository;
    private final PhotoMapper mapper;


    @Autowired
    PhotoServiceImpl(PhotoRepository repository,
                     PhotoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public String addPhoto(PhotoDto photoDto) throws IOException {
        File photoFile = File.builder()
                .name(photoDto.getFileName())
                .bytes(photoDto.getFileBytes())
                .build();
        System.out.println();
        Photo photo = new Photo();
        photo.setFile(photoFile);
        photo.setIdCar(photoDto.getIdCar());
        Photo photo1 = repository.insert(photo);
        System.out.println(photo1.getFile().getName());
        return photo1.getFile().getName();
    }

    @Override
    public void deletePhotoById(String idPhoto) {
        repository.deleteById(idPhoto);
    }

    @Override
    public List<PhotoResponse> getPhoto(Integer idCar) {
        List<PhotoResponse> photos = repository.findAllByIdCar(idCar)
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
        return photos;
    }

}
