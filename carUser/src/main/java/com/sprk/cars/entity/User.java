package com.sprk.cars.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;

    private String phone;

    private String address;

    @ManyToMany(mappedBy = "users",cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH})
    List<Car> cars= new ArrayList<>();
    public void addCar(Car car){
        cars.add(car);
        car.getUsers().add(this);
    }
}
