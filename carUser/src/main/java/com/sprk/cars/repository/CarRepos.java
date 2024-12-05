package com.sprk.cars.repository;

import com.sprk.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepos extends JpaRepository<Car, Integer> {
}
