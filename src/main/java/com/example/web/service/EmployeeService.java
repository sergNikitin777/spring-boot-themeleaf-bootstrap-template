package com.example.web.service;

import com.example.persistance.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(Integer id);

    Integer addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

    void deleteAllEmployee();

    List<Employee> findAllByCompanyID(Integer id);
}
