package com.sprk.cars.mapper;

import com.sprk.cars.dto.CarDto;
import com.sprk.cars.dto.CarWithUserDto;
import com.sprk.cars.dto.UserDto;
import com.sprk.cars.entity.Car;
import com.sprk.cars.entity.User;

import java.util.ArrayList;
import java.util.List;

public class CarMapper {

    public static CarDto convertCarToCarDto(Car car, CarDto carDto){
        carDto.setCarId(car.getCarId());
        carDto.setCarName(car.getCarName());
        carDto.setBrand(car.getBrand());
        carDto.setPrice(car.getPrice());
        return carDto;
    }

    public static Car convertCarDtoToCar(CarDto carDto, Car car){
        car.setCarId(carDto.getCarId());
        car.setCarName(carDto.getCarName());
        car.setBrand(carDto.getBrand());
        car.setPrice(carDto.getPrice());
        return car;
    }

    public static CarWithUserDto convertCarToCarWithUserDto(Car car, CarWithUserDto carWithUserDto){
        carWithUserDto.setCarName(car.getCarName());
        carWithUserDto.setBrand(car.getBrand());
        carWithUserDto.setPrice(car.getPrice());

        List<User> users=car.getUsers();
        List<UserDto> userDtos= new ArrayList<>();

        for(User user: users){
            UserDto userDto= UserMapper.convertUserToUserDto(user, new UserDto());
            userDtos.add(userDto);
        }

        carWithUserDto.setUsers(userDtos);
        return carWithUserDto;
    }
}
