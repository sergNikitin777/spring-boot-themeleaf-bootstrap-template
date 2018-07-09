package com.example.web.service;

import com.example.persistance.entity.*;
import com.example.persistance.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final StatusRepository statusRepository;
    private final DeviceGroupRepository deviceGroupRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract findById(Integer id) {
        return contractRepository.findOne(id);
    }


    @Override
    public Integer addContract(Contract contract) {
        return contractRepository.save(contract).getId();
    }

    @Override
    public void updateContract(Contract contract) {
        contractRepository.save(contract);
    }

    @Override
    public void deleteContract(Integer id) {
        contractRepository.delete(id);
    }

}
