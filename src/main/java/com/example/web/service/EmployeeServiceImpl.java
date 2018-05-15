package com.example.web.service;

import com.example.persistance.entity.*;
import com.example.persistance.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public Integer addEmployee(Employee employee) {
        return employeeRepository.save(employee).getId();
    }

    @Override
    public void updateEmployee(Employee employee) { employeeRepository.save(employee); }

    @Override
    public void deleteEmployee(Integer id) { employeeRepository.delete(id); }

    @Override
    public void deleteAllEmployee() { employeeRepository.deleteAll(); }
}
