package org.kamenchuk.dto.extraUsersDataDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtraUsersDataCreateRequest {
    private Long id;
    private String idPassport;
    private String name;
    private String lastname;
    private String drivingLicense;
    private String phone;
    private Date registerDate;
}
