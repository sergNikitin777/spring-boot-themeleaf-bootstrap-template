package com.example.web.service;

import com.example.persistance.entity.AutoDriver;
import com.example.web.pojo.AutoDriverPojo;

import java.util.List;

public interface AutoDriverService {
    List<AutoDriver> findAll();

    AutoDriver findById(Integer id);

    Integer addAutoDriver(AutoDriverPojo autoDriver);

    Integer addAuto(Integer driverId,Integer autoId);

    void updateAutoDriver(AutoDriverPojo autoDriver, Integer id);

    void deleteAutoDriver(Integer id);

    void deleteAllAutoDriver();
}