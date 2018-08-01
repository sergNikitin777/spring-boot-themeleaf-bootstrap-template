package com.example.web.service;

import com.example.persistance.entity.Ekt;
import com.example.persistance.repository.EktRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EktServiceImpl implements EktService {

    private final EktRepository ektRepository;

    @Override
    public List<Ekt> findAll() {
        return ektRepository.findAll();
    }

    @Override
    public Ekt findById(Integer id) {
        return ektRepository.findOne(id);
    }

    @Override
    public Integer addEkt(Ekt ekt) {
        return ektRepository.save(ekt).getId();
    }

    @Override
    public Ekt updateEkt(Ekt ekt) {
        Ekt temp = new Ekt();
        if (ekt.getId() != null){
            temp.setId(ekt.getId());
        }
        if (ekt.getAddress() != null){
            temp.setAddress(ekt.getAddress());
        }
        if (ekt.getDevice() != null){
            temp.setDevice(ekt.getDevice());
        }
        if (ekt.getNumber() != null){
            temp.setNumber(ekt.getNumber());
        }
        return ektRepository.save(temp);
    }

    @Override
    public void deleteEkt(Integer id) {
        ektRepository.delete(id);
    }

}
