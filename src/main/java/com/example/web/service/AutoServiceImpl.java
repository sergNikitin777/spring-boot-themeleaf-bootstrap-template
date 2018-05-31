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
public class AutoServiceImpl implements AutoService {
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
        return autoRepository.save(new Auto(auto.getMark(), auto.getModel(), auto.getLicensePlate(), auto.getType())).getId();
    }

    @Override
    public void deleteAuto(Integer id) {
        autoRepository.delete(id);
    }

    @Override
    public void deleteAllAuto() {
        autoRepository.deleteAll();
    }

    @Override
    public void updateAuto(AutoPojo auto, Integer id) {
        if (auto != null && id != null) {
            Auto auto1 = autoRepository.findOne(id);
            if (auto.getLicensePlate() != null) auto1.setLicensePlate(auto.getLicensePlate());
            if (auto.getMark() != null) auto1.setMark(auto.getMark());
            if (auto.getModel() != null) auto1.setModel(auto.getModel());
            if (auto.getType() != null) auto1.setType(auto.getType());
            autoRepository.save(auto1);
        }
    }
}