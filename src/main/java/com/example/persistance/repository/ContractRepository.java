package com.example.persistance.repository;

import com.example.persistance.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
