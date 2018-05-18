package com.example.web.service;


import com.example.persistance.entity.Auto;
import com.example.web.pojo.AutoPojo;

import java.util.List;

public interface AutoService {
    List<Auto> findAll();

    Auto findById(Integer id);

    Integer addAuto(AutoPojo auto);

    void updateAuto(Auto auto);

    void deleteAuto(Integer id);

    void deleteAllAuto();
}