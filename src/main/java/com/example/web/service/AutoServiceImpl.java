package com.example.web.service;

import com.example.persistance.entity.Auto;
import com.example.persistance.repository.AutoRepository;
import com.example.web.pojo.AutoPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class AutoServiceImpl implements AutoService{
    private final AutoRepository autoRepository;

    @Override
    public List<Auto> findAll() {
        return autoRepository.findAll();
    }

    @Override
    public Auto findById(Integer id) {
        return autoRepository.findOne(id);
    }

    @Override
    public Integer addAuto(AutoPojo auto) {
        return autoRepository.save(new Auto(auto.getMark(),auto.getModel(),auto.getLicensePlate(),auto.getType())).getId();
    }

    @Override
    public void updateAuto(Auto auto) { autoRepository.save(auto); }

    @Override
    public void deleteAuto(Integer id) { autoRepository.delete(id); }

    @Override
    public void deleteAllAuto() { autoRepository.deleteAll(); }
}