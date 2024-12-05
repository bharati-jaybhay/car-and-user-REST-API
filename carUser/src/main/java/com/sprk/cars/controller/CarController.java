package com.sprk.cars.controller;

import com.sprk.cars.dto.CarDto;
import com.sprk.cars.entity.Car;
import com.sprk.cars.mapper.CarMapper;
import com.sprk.cars.repository.CarRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CarController {
    @Autowired
    private CarRepos carRepos;

    @PostMapping("/saveCar")
    public CarDto saveCar(@RequestBody CarDto carDto){
        Car car= CarMapper.convertCarDtoToCar(carDto, new Car());
        carRepos.save(car);
        carDto.setCarId(car.getCarId());
        return carDto;
    }

    @GetMapping("/getAllCars")
    public List<CarDto> getAllCars(){
        List<Car> cars=carRepos.findAll();
        List<CarDto> carDtos=new ArrayList<>();

        for(Car car: cars){
            CarDto carDto=CarMapper.convertCarToCarDto(car, new CarDto());
            carDtos.add(carDto);
        }
        return carDtos;
    }

    @GetMapping("/getCarById/{carId}")
    public CarDto getCarById(@PathVariable int carId){
        Car car= carRepos.findById(carId).orElseThrow(() -> new RuntimeException("Car not found!!"));
        CarDto carDto=CarMapper.convertCarToCarDto(car, new CarDto());
        return carDto;
    }

    @DeleteMapping("/deleteCarById/{carId}")
    public String deleteById(@PathVariable int carId) {
        if (!carRepos.existsById(carId)) {
            throw new RuntimeException("Car not found!!");
        }
        carRepos.deleteById(carId);
        return "Car deleted successfully";
    }

    @PutMapping("/updateCar/{carId}")
    public CarDto updateCarById(@PathVariable int carId, @RequestBody CarDto carDto){
        Car existingCar= carRepos.findById(carId).orElseThrow(()-> new RuntimeException("Student is not found!!"));

        Optional.ofNullable(carDto.getCarName()).ifPresent(existingCar::setCarName);
        Optional.ofNullable(carDto.getBrand()).ifPresent(existingCar::setBrand);
        Optional.ofNullable(carDto.getPrice()).ifPresent(existingCar::setPrice);

        return CarMapper.convertCarToCarDto(carRepos.save(existingCar), new CarDto());

    }
}
