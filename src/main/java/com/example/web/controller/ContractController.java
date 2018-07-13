package com.example.web.controller;

import com.example.persistance.entity.Contract;
import com.example.persistance.entity.Device;
import com.example.web.pojo.DevicePojo;
import com.example.web.service.ContractService;
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
public class ContractController {

    private final ContractService contractService;

    @RequestMapping(value = "/admin/contracts", method = RequestMethod.GET)
    public ResponseEntity<List<Contract>> getContractList() {
        log.debug("getContractList");
        return new ResponseEntity<>(contractService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/contract/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contract> getContractById(@PathVariable("id") Integer id) {
        log.debug("getContractById");
        return new ResponseEntity<>(contractService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/contract/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addContract(Contract contract) {
        log.debug("addContract");
        try {
            return new ResponseEntity<>(contractService.addContract(contract), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/admin/contract/update")
    public ResponseEntity  updateContract(@RequestBody Contract contract) {
        log.debug("updateContract");
        try {
            return new ResponseEntity<>(contractService.updateContract(contract), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/contract/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteContract(@PathVariable("id") Integer id) {
        log.debug("deleteContract");
        try {
            contractService.deleteContract(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
