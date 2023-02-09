package org.kamenchuk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UForAdminOrderDto {
    private Long id;
    private Boolean status;
    private String adminsLogin;
    private String refuseReason;
}
