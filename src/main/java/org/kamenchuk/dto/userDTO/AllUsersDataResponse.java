package org.kamenchuk.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.models.roleDTO.RoleResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllUsersDataResponse {
    private Long id;
    private String login;
    private RoleResponse roleResponse;
    private ExtraUserDataUpdateRequest extraRequest;
}
