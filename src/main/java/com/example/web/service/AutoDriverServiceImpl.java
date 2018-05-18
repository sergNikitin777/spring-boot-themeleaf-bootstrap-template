package com.example.web.service;

import com.example.persistance.entity.AutoDriver;
import com.example.persistance.repository.AutoDriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class AutoDriverServiceImpl implements AutoDriverService{
    private final AutoDriverRepository autoRepository;

    @Override
    public List<AutoDriver> findAll() {
        return autoRepository.findAll();
    }

    @Override
    public AutoDriver findById(Integer id) {
        return autoRepository.findOne(id);
    }

    @Override
    public Integer addAutoDriver(AutoDriver autoDriver) {
        return autoRepository.save(autoDriver).getId();
    }

    @Override
    public void updateAutoDriver(AutoDriver autoDriver) { autoRepository.save(autoDriver); }

    @Override
    public void deleteAutoDriver(Integer id) { autoRepository.delete(id); }

    @Override
    public void deleteAllAutoDriver() { autoRepository.deleteAll(); }
}