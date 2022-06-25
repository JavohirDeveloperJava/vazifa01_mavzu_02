package com.example.vazifa01_mavzu_02.repository;

import com.example.vazifa01_mavzu_02.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
    boolean existsByName(String name);
}
