package com.example.web.service;

import com.example.persistance.entity.Company;
import com.example.persistance.entity.Employee;
import com.example.persistance.entity.User;
import com.example.persistance.repository.CompanyRepository;
import com.example.persistance.repository.EmployeeRepository;
import com.example.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

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
        Employee tempEmployee = new Employee(employee.getFio(), employee.getPosition(), employee.getAccessNeeded());
        if (employee.getCompany() != null) {
            Company company = companyRepository.getOne(employee.getCompany().getId());
            if (company != null) {
                tempEmployee.setCompany(company);
            }
        }
        if (employee.getUser() != null) {
            User user = userRepository.getOne(employee.getCompany().getId());
            if (user != null) {
                tempEmployee.setUser(user);
            }
        }
        return employeeRepository.save(tempEmployee).getId();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Company company = null;
        if (employee.getCompany() != null) {
            if (employee.getCompany().getId() != null) {
                company = companyRepository.getOne(employee.getCompany().getId());
            }
        }
        if (company != null) {
            employee.setCompany(company);
        }
        Employee temp = new Employee();
        if (employee.getId() != null) {
            temp.setId(employee.getId());
        }
        if (employee.getFio() != null) {
            temp.setFio(employee.getFio());
        }
        if (employee.getPosition() != null) {
            temp.setPosition(employee.getPosition());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.delete(id);
    }

    @Override
    public void deleteAllEmployee() {
        employeeRepository.deleteAll();
    }

    @Override
    public List<Employee> findAllByCompanyID(Integer id) {
        return employeeRepository.findAllByCompanyId(id);
    }
}
