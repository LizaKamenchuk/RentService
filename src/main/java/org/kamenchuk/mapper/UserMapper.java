package org.kamenchuk.mapper;

import org.kamenchuk.dto.CreateUserDto;
import org.kamenchuk.dto.UserDto;
import org.kamenchuk.models.User;

public class UserMapper {
    public UserDto toUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .role(user.getRole().getRole())
                .passportIDN(user.getIdPassport())
                .name(user.getName())
                .lastname(user.getLastname())
                .dateOfBirth(user.getDateOfBirth())
                .drivingLicense(user.getDrivingLicense())
                .phone(user.getPhone())
                .registerDate(user.getRegisterDate())
                .build();
    }

    public CreateUserDto toCreateUserDto(User user){
        return CreateUserDto.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public User toUser(CreateUserDto userDto){
        return User.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }


}
