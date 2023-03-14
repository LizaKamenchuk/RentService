package org.kamenchuk.dto.extraUsersDataDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtraUserDataUpdateRequest {
    private Long id;
    private String idPassport;
    private String name;
    private String lastname;
    private String drivingLicense;
    private String phone;
}
