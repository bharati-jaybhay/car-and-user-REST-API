package com.example.car_user.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
public class Car {

    @Id
    private int carId;

}
