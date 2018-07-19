package com.example.web.service;


import com.example.persistance.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    Company findById(Integer id);

    Integer addCompany(Company company);

    Company updateCompany(Company company);

    void deleteCompany(Integer id);
}
