package org.kamenchuk.models;

import lombok.Data;

@Data
public class Car {
    private int id;
    private String carNumber;
    private int price;
    private String limitations;
    private int idImage;
    private String mark;
    private String model;
}
