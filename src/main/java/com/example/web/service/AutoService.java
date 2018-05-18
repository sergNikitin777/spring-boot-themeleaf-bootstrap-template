package com.example.web.service;


import com.example.persistance.entity.Auto;

import java.util.List;

public interface AutoService {
    List<Auto> findAll();

    Auto findById(Integer id);

    Integer addAuto(Auto auto);

    void updateAuto(Auto auto);

    void deleteAuto(Integer id);

    void deleteAllAuto();
}