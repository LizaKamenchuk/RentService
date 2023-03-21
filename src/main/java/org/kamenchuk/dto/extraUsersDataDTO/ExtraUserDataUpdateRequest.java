package org.kamenchuk.dto.extraUsersDataDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtraUserDataUpdateRequest {
    private String idPassport;
    private String name;
    private String lastname;
    private String drivingLicense;
    private LocalDate dateOfBirth;
    private String phone;
}
