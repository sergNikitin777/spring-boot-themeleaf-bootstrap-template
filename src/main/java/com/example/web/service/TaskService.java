package com.example.web.service;


import com.example.persistance.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();

    Task findById(Integer id);

    Task updateTask(Task task);

    void deleteTask(Integer id);

    Integer addTask(Task task);
}
