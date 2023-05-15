package org.kamenchuk.dto.extraUsersDataDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtraUserDataUpdateRequest {
    @Nullable
    private String idPassport;
    @Nullable
    private String name;
    @Nullable
    private String lastname;
    @Nullable
    private String drivingLicense;
    @Nullable
    private LocalDate dateOfBirth;
    @Nullable
    private String phone;
}
