package org.kamenchuk.models;

import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private long id;
    private String login;
    private String password;
    private int role;
    private String idPassport;//=idExtraUsersData
}

