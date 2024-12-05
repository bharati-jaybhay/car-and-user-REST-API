package com.sprk.cars.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarWithUserDto {

    private String carName;

    private String brand;

    private double price;

    private List<UserDto> users;
}
