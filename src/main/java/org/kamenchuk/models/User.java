package org.kamenchuk.models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String login;
    private String password;
    private Role role;

    private Long idExtraUsersData;
    private String name;
    private String lastname;
    private Date dateOfBirth;
    private String drivingLicense;
    private String phone;
    private Date registerDate;
}

