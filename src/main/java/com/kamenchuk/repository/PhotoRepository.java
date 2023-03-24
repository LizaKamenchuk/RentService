package com.kamenchuk.repository;

import com.kamenchuk.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String> {
    List<Photo> findAllByIdCar(Integer idCar);
}
