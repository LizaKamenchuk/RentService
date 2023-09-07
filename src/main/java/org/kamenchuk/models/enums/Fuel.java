package org.kamenchuk.models.enums;

import lombok.Getter;

@Getter
public enum Fuel {
    DIESEL("дизельное топливо"),
    PETROL("бензин"),
    GAS("газ"),
    ELECTRIC("электричество");
    private final String fuelType;

    Fuel(String fuelType) {
        this.fuelType = fuelType;
    }
}
