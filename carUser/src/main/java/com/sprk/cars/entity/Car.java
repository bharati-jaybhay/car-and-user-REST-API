package com.sprk.cars.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;

    private String carName;

    private String brand;

    private double price;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    List<User> users=new ArrayList<>();

    public void addCar(User user){
        users.add(user);
        user.getCars().add(this);
    }
}
