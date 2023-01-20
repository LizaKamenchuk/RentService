package org.kamenchuk.models;

import lombok.Data;

import java.sql.Date;

@Data
public class ExtraUserData {
    private String idPassport;//=idExtraUsersData
    private String name;
    private String lastname;
    private Date dateOfBirth;
    private String drivingLicense;
    private String phone;
    private Date registerDate;
}
