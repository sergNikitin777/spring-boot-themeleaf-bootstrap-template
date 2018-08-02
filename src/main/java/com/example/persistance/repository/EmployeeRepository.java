package com.example.persistance.repository;

import com.example.persistance.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByCompanyID(Integer id);
}
