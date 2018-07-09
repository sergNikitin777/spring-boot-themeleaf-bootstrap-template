package com.example.persistance.repository;

import com.example.persistance.entity.Contract;
import com.example.persistance.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
