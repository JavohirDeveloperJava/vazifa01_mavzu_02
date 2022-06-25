package com.example.vazifa01_mavzu_02.repository;

import com.example.vazifa01_mavzu_02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
}
