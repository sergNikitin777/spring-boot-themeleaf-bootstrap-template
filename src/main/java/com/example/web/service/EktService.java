package com.example.web.service;


import com.example.persistance.entity.Ekt;

import java.util.List;

public interface EktService {

    List<Ekt> findAll();

    Ekt findById(Integer id);

    Integer addEkt(Ekt customer);

    Ekt updateEkt(Ekt customer);

    void deleteEkt(Integer id);
}
