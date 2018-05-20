package com.example.web.service;

import com.example.persistance.entity.Auto;
import com.example.persistance.entity.AutoDriver;
import com.example.persistance.repository.AutoDriverRepository;
import com.example.persistance.repository.AutoRepository;
import com.example.web.pojo.AutoDriverPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class AutoDriverServiceImpl implements AutoDriverService {
    private final AutoDriverRepository autoDriverRepository;

    private final AutoRepository autoRepository;

    @Override
    public List<AutoDriver> findAll() {
        return autoDriverRepository.findAll();
    }

    @Override
    public AutoDriver findById(Integer id) {
        return autoDriverRepository.findOne(id);
    }

    @Override
    public Integer addAutoDriver(AutoDriverPojo autoDriver) {

        return autoDriverRepository.save(new AutoDriver(null, autoDriver.getFirstName(), autoDriver.getSurname(),
                autoDriver.getPatronymic(), autoDriver.getPhoneNumber())).getId();
    }

    @Override
    public Integer addAuto(Integer driverId, Integer autoId) {

        AutoDriver autoDriver = autoDriverRepository.findOne(driverId);

        Auto auto = autoRepository.findOne(autoId);

        autoDriver.getAutoList().add(auto);

        autoDriverRepository.save(autoDriver);

        return autoDriver.getId();
    }

    @Override
    public void updateAutoDriver(AutoDriver autoDriver) {
        autoDriverRepository.save(autoDriver);
    }

    @Override
    public void deleteAutoDriver(Integer id) {
        autoDriverRepository.delete(id);
    }

    @Override
    public void deleteAllAutoDriver() {
        autoDriverRepository.deleteAll();
    }
}