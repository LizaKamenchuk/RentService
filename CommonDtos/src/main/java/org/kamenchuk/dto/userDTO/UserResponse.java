package org.kamenchuk.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kamenchuk.dto.roleDTO.RoleResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String login;
    private RoleResponse roleResponse;
    private Long idED;
}
