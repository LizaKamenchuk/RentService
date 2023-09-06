package org.kamenchuk.service;

import org.kamenchuk.models.ExtraDataCar;


public interface ExtraDataCarService {
    ExtraDataCar save (ExtraDataCar extraDataCar);
    void delete (Integer id);
    ExtraDataCar getById(Integer id);
}
