package com.example.web.service;

import com.example.persistance.entity.Auto;
import com.example.persistance.repository.AutoRepository;
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
    public Integer addAuto(Auto auto) {
        return autoRepository.save(auto).getId();
    }

    @Override
    public void updateAuto(Auto auto) { autoRepository.save(auto); }

    @Override
    public void deleteAuto(Integer id) { autoRepository.delete(id); }

    @Override
    public void deleteAllAuto() { autoRepository.deleteAll(); }
}