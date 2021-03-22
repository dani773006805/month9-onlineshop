package com.attractor.onlineshop.dto.mapper;

import com.attractor.onlineshop.dto.UserDto;
import com.attractor.onlineshop.model.User;

public class UserMapper {
    public static User fromDto(UserDto userDto){
        return User.builder().username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
    public static UserDto toDto(User user){
        return UserDto.builder().username(user.getUsername())
                .build();
    }
}
