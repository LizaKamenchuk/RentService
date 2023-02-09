package org.kamenchuk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kamenchuk.models.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UCUserDto {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private Long idExtraUsersData;
}
