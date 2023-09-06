package org.kamenchuk.service;

import org.kamenchuk.models.Fuel;

public interface FuelService {
    String save (String fuelType);
    void deleteById (Integer id);
    Fuel findByFuelType(String fuelType);
}
