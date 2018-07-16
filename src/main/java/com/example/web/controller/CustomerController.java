package com.example.web.controller;

import com.example.persistance.entity.Contract;
import com.example.persistance.entity.Customer;
import com.example.web.service.CustomerService;
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
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping(value = "/admin/customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getContractList() {
        log.debug("getCustomerList");
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getContractById(@PathVariable("id") Integer id) {
        log.debug("getCustomerById");
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/customer/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addCustomer(Customer customer) {
        log.debug("addCustomer");
        try {
            return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/admin/customer/update")
    public ResponseEntity  updateCustomer(@RequestBody Customer customer) {
        log.debug("updateCustomer");
        try {
            return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCustomer(@PathVariable("id") Integer id) {
        log.debug("deleteCustomer");
        try {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
