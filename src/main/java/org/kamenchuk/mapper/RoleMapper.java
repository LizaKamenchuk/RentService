package org.kamenchuk.mapper;

import org.kamenchuk.dto.RoleDto;
import org.kamenchuk.models.Role;

public class RoleMapper {
    public RoleDto toRoleDto(Role role){
        return RoleDto.builder()
                .role(role.getRole())
                .build();
    }
}
