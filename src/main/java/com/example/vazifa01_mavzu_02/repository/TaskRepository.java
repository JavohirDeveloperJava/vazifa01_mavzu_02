package com.example.vazifa01_mavzu_02.repository;

import com.example.vazifa01_mavzu_02.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
