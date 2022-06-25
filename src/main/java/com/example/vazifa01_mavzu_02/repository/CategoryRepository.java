package com.example.vazifa01_mavzu_02.repository;

import com.example.vazifa01_mavzu_02.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByName(String name);
}
