package org.kamenchuk.models.enums;

import lombok.Getter;

@Getter
public enum CarClass {
    MIDDLE("средний"),
    BUSINESS("бизнес"),
    PREMIUM("премиум"),
    SPORT("спорт");
    private final String carClassType;
    CarClass(String carClassType){
        this.carClassType = carClassType;
    }

}
