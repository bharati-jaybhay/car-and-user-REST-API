package com.sprk.cars.repository;

import com.sprk.cars.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos extends JpaRepository<User, Integer> {
}
