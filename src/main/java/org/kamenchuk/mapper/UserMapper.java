package org.kamenchuk.mapper;

import org.kamenchuk.dto.userDTO.AllUsersDataResponse;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roleResponse.role", source = "role.role")
    @Mapping(target = "idED",source = "extraUsersData.id")
    UserResponse toDto(User entity);

    @Mapping(target = "role.role", source = "roleResponse.role")
    User toUser(UserResponse dto);

    @Mapping(target = "extraUsersData.name",source = "name")
    @Mapping(target = "extraUsersData.lastname",source = "lastname")
    User save(UserCreateRequest entity);

    //TODO QUESTION
    @Mapping(target = "roleResponse.role",source = "role.role")
    @Mapping(target = "extraRequest",source = "extraUsersData")
    AllUsersDataResponse toAllDto(User entity);


}
