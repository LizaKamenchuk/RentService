package org.kamenchuk.mapper;

import org.kamenchuk.dto.userDTO.*;
import org.kamenchuk.models.User;
import org.kamenchuk.models.UserAuthResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roleResponse.role", source = "role.role")
    @Mapping(target = "idED", source = "extraUsersData.id")
    UserResponse toDto(User entity);

    @Mapping(target = "role.role", source = "roleResponse.role")
    @Mapping(target = "extraUsersData.id", source = "idED")
    User toUser(UserResponse dto);

    @Mapping(target = "extraUsersData.name", source = "name")
    @Mapping(target = "extraUsersData.lastname", source = "lastname")
    User save(UserCreateRequest entity);

    @Mapping(target = "roleResponse.role", source = "role.role")
    @Mapping(target = "extraRequest", source = "extraUsersData")
    AllUsersDataResponse toAllDto(User entity);

    @Mapping(target = "role", source = "role.role")
    UserAuthResponse toAuthDto(User entity);

    @Mapping(target = "name",source = "extraUsersData.name")
    @Mapping(target = "lastname",source = "extraUsersData.lastname")
    AdminDocumentDto toAdminDto(User entity);
    @Mapping(target = "lastname",source = "extraUsersData.lastname")
    @Mapping(target = "name",source = "extraUsersData.name")
    @Mapping(target = "drivingLicense",source = "extraUsersData.drivingLicense")
    @Mapping(target = "idPassport",source = "extraUsersData.idPassport")
    UserDocumentDto toUserDocumentDto(User user);

}
