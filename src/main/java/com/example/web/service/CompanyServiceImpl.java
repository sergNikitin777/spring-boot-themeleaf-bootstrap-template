package com.example.web.service;

import com.example.persistance.entity.Company;
import com.example.persistance.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Integer id) {
        return companyRepository.findOne(id);
    }

    @Override
    public Integer addCompany(Company company) {
        Company tempCompany= new Company(company.getName(), company.getEmployeeCount(), company.getContractCount(),company.getAddress(),company.getDirector(),company.getPhone(),company.getEmail());
        return companyRepository.save(tempCompany).getId();
    }

    @Override
    public Company updateCompany(Company company) {
        Company temp = new Company();
        if (company.getId()!=null){
            temp.setId(company.getId());
        }
        if (company.getName()!=null){
            temp.setName(company.getName());
        }
        if (company.getContractCount()!=null){
            temp.setContractCount(company.getContractCount());
        }
        if (company.getEmployeeCount()!=null){
            temp.setEmployeeCount(company.getEmployeeCount());
        }
        return companyRepository.save(temp);
    }

    @Override
    public void deleteCompany(Integer id) {
        companyRepository.delete(id);
    }

}
