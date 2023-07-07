package org.kamenchuk.mapper;

import org.kamenchuk.dto.roleDTO.RoleRequest;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "role",source = "role")
    Role toRoleFromResponse(RoleResponse roleResponse);

    @Mapping(target = "role",source = "role")
    RoleResponse toDtoResponse(Role role);

    @Mapping(target = "id",source = "id")
    Role toRoleFromRequest(RoleRequest roleRequest);

    @Mapping(target = "id",source = "id")
    RoleRequest toDtoRequest(Role role);
}
