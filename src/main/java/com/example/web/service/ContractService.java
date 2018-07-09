package com.example.web.service;


import com.example.persistance.entity.Contract;
import com.example.persistance.entity.Device;
import com.example.web.pojo.DevicePojo;

import java.util.List;

public interface ContractService {
    List<Contract> findAll();

    Contract findById(Integer id);

    Integer addContract(Contract contract);

    void updateContract(Contract contract);

    void deleteContract(Integer id);
}
