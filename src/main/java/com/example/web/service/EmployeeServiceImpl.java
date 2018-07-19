package com.example.web.service;

import com.example.persistance.entity.Company;
import com.example.persistance.entity.Employee;
import com.example.persistance.repository.CompanyRepository;
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
    private final CompanyRepository companyRepository;

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
       Company company = companyRepository.getOne(employee.getCompany().getId());
       employee.setCompany(company);
       return employeeRepository.save(employee).getId();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Company company = null;
        if (employee.getCompany()!=null){
            if (employee.getCompany().getId()!=null){
                company = companyRepository.getOne(employee.getCompany().getId());
            }
        }
        if (company!=null) {
            employee.setCompany(company);
        }
        Employee temp = new Employee();
        if (employee.getId()!=null){
            temp.setId(employee.getId());
        }
        if (employee.getName()!=null){
            temp.setName(employee.getName());
        }
        if (employee.getPatronymic()!=null){
            temp.setPatronymic(employee.getPatronymic());
        }
        if (employee.getSurname()!=null){
            temp.setSurname(employee.getSurname());
        }
        if (employee.getPosition()!=null){
            temp.setPosition(employee.getPosition());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) { employeeRepository.delete(id); }

    @Override
    public void deleteAllEmployee() { employeeRepository.deleteAll(); }
}
