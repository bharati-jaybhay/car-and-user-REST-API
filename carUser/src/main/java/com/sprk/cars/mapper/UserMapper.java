package com.sprk.cars.mapper;

import com.sprk.cars.dto.UserDto;
import com.sprk.cars.entity.User;

public class UserMapper {

    public static UserDto convertUserToUserDto( User user,UserDto userDto) {

        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        return userDto;
    }

    public static User convertUserDtoToUser(UserDto userDto, User user){

        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        return user;
    }


}
