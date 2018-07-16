package com.example.web.service;


import com.example.persistance.entity.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> findAll();

    Contract findById(Integer id);

    Integer addContract(Contract contract);

    Contract updateContract(Contract contract);

    void deleteContract(Integer id);
}
