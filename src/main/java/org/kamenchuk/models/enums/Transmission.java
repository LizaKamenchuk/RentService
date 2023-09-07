package org.kamenchuk.models.enums;

import lombok.Getter;

@Getter
public enum Transmission {

    AUTOMATIC("автоматическая"),
    MECHANICAL("механическая");
    private final String transmissionType;
    Transmission(String transmissionType){
        this.transmissionType = transmissionType;
    }
}
