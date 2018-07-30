package com.example.web.controller;

import com.example.persistance.entity.Company;
import com.example.web.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @RequestMapping(value = "/admin/companies", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> getCompanyList() {
        log.debug("getCompanyList");
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

        @RequestMapping(value = "/admin/company/{id}", method = RequestMethod.GET)
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") Integer id) {
        log.debug("getCompanyById");
        return new ResponseEntity<>(companyService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/company/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addCompany(Company company) {
        log.debug("addCompany");
        try {
            return new ResponseEntity<>(companyService.addCompany(company), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/admin/company/update")
    public ResponseEntity updateCompany(@RequestBody Company company) {
        log.debug("updateCompany");
        try {
            return new ResponseEntity<>(companyService.updateCompany(company), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/company/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCompany(@PathVariable("id") Integer id) {
        log.debug("deleteCompany");
        try {
            companyService.deleteCompany(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
