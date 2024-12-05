package com.sprk.cars.controller;

import com.sprk.cars.dto.UserDto;
import com.sprk.cars.entity.User;
import com.sprk.cars.mapper.UserMapper;
import com.sprk.cars.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepos userRepos;

    @PostMapping("/saveUser")
    public UserDto saveUser(@RequestBody UserDto userDto){
       User user= UserMapper.convertUserDtoToUser(userDto, new User());
       userRepos.save(user);
       userDto.setUserId(user.getUserId());
       return userDto;

    }

    @GetMapping("/getAll")
    public List<UserDto> getAllUsers(){
        List<User> users = userRepos.findAll();
        List<UserDto> userDtos= new ArrayList<>();

        for(User user:users){
            UserDto userDto=UserMapper.convertUserToUserDto(user, new UserDto());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @GetMapping("/getUserById/{userId}")
    public UserDto getUserById(@PathVariable int userId){
        User user =userRepos.findById(userId).orElseThrow(() -> new RuntimeException("User not found!!"));
        UserDto userDto1= UserMapper.convertUserToUserDto(user, new UserDto());

        return userDto1;
    }

    @DeleteMapping("/deleteById/{userId}")
    public String deleteById(@PathVariable int userId){
        if(!userRepos.existsById(userId)){
            throw new RuntimeException("user not found!!");
        }
        userRepos.deleteById(userId);
        return "User deleted successfully!!";
    }

    @PutMapping("/updateUser/{userId}")
    public UserDto updateUserById(@PathVariable int userId, @RequestBody UserDto userDto){
        User existingUser=userRepos.findById(userId).orElseThrow(()-> new RuntimeException("User not found!!"));

        Optional.ofNullable(userDto.getUserName()).ifPresent(existingUser::setUserName);
        Optional.ofNullable(userDto.getPhone()).ifPresent(existingUser::setPhone);
        Optional.ofNullable(userDto.getAddress()).ifPresent(existingUser::setAddress);

        return UserMapper.convertUserToUserDto(userRepos.save(existingUser), new UserDto());

    }
}
