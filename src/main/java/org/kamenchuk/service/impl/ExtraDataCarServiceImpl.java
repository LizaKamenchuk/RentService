package org.kamenchuk.service.impl;

import org.kamenchuk.repository.ExtraDataCarRepository;
import org.kamenchuk.models.ExtraDataCar;
import org.kamenchuk.service.ExtraDataCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraDataCarServiceImpl implements ExtraDataCarService {
    private final ExtraDataCarRepository extraDataCarRepository;

    @Autowired
    public ExtraDataCarServiceImpl(ExtraDataCarRepository extraDataCarRepository) {
        this.extraDataCarRepository = extraDataCarRepository;
    }

    @Override
    public ExtraDataCar save(ExtraDataCar extraDataCar) {
        return extraDataCarRepository.save(extraDataCar);
    }

    @Override
    public void delete(Integer id) {
        extraDataCarRepository.deleteById(id);
    }
}
