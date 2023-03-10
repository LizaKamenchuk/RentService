package org.kamenchuk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String login;
    private String role;
    private String passportIDN;
    private String name;
    private String lastname;
    private Date dateOfBirth;
    private String drivingLicense;
    private String phone;
    private Date registerDate;
}
