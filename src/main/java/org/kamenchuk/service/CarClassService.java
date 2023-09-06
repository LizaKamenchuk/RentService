package org.kamenchuk.service;

import org.kamenchuk.models.CarClass;

public interface CarClassService {
    String save (String carClassType);
    void deleteById (Integer id);
    CarClass findByCarClassType(String carClassType);
}
