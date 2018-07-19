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
        return companyRepository.save(company).getId();
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Integer id) {
        companyRepository.delete(id);
    }

}