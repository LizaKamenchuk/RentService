package org.kamenchuk.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDocumentDto {
    private String name;
    private String lastname;
    private String idPassport;
    private String drivingLicense;
}
