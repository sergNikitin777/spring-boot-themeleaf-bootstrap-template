package com.example.persistance.repository;

import com.example.persistance.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer>{
}
