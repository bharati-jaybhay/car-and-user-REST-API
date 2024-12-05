package com.sprk.cars.controller;

import com.sprk.cars.dto.CarWithUserDto;
import com.sprk.cars.entity.Car;
import com.sprk.cars.entity.User;
import com.sprk.cars.mapper.CarMapper;
import com.sprk.cars.repository.CarRepos;
import com.sprk.cars.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private CarRepos carRepos;

    @Autowired
    private UserRepos userRepos;

    @PostMapping("/addCarToUser/{carId}/{userId}")
    public String addCarToUser(@PathVariable int carId, @PathVariable int userId){
        User user=userRepos.findById(userId).orElseThrow(()-> new RuntimeException("User not found!!"));
        Car car=carRepos.findById(carId).orElseThrow(()-> new RuntimeException("Car not found!!"));

        user.addCar(car);
        carRepos.save(car);
        return "Car added successfully!!";

    }

    @GetMapping("/getCarWithUser/{carId}")
    public CarWithUserDto getCarWithUser(@PathVariable int carId){
        Car car= carRepos.findById(carId).orElseThrow(()-> new RuntimeException("Car not found!!"));
        CarWithUserDto carWithUserDto= CarMapper.convertCarToCarWithUserDto(car, new CarWithUserDto());

        return carWithUserDto;
    }
}