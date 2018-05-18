package com.example.web.service;


import com.example.persistance.entity.Auto;
import com.example.persistance.entity.AutoDriver;

import java.util.List;

public interface AutoDriverService {
    List<AutoDriver> findAll();

    AutoDriver findById(Integer id);

    Integer addAutoDriver(AutoDriver autoDriver);

    void updateAutoDriver(AutoDriver autoDriver);

    void deleteAutoDriver(Integer id);

    void deleteAllAutoDriver();
}