package org.kamenchuk.mapper;

import org.kamenchuk.dto.UCUserDto;
import org.kamenchuk.dto.AllUsersDataDto;
import org.kamenchuk.models.User;

public class UserMapper {
    public AllUsersDataDto toUserDto(User user){
        return null;
//        return AllUsersDataDto.builder()
//                .id(user.getId())
//                .login(user.getLogin())
//                .role(user.getRole().getRole())
//                .passportIDN(user.getExtraUsersData().getIdPassport())
//                .name(user.getExtraUsersData().getName())
//                .lastname(user.getExtraUsersData().getLastname())
//                .dateOfBirth(user.getExtraUsersData().getDateOfBirth())
//                .drivingLicense(user.getExtraUsersData().getDrivingLicense())
//                .phone(user.getExtraUsersData().getPhone())
//                .registerDate(user.getExtraUsersData().getRegisterDate())
//                .build();
    }

    public UCUserDto toCreateUserDto(User user){
        return new UCUserDto();
//        return UCUserDto.builder()
//                .login(user.getLogin())
//                .password(user.getPassword())
//                .role(user.getRole())
//                .build();
    }

    public User toUser(UCUserDto userDto){
        return new User();
//        return User.builder()
//                .login(userDto.getLogin())
//                .password(userDto.getPassword())
//                .role(userDto.getRole())
//                .build();
    }


}
