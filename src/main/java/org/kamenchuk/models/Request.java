package org.kamenchuk.models;

import lombok.Data;

import java.sql.Date;

@Data
public class Request {
    private long id;
    private Date startDate;
    private Date finishDate;
    private boolean status;
    private int idCar;
    private long idUser;
    private String refuseReason;
}
