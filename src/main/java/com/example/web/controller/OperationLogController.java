package com.example.web.controller;

import com.example.persistance.entity.OperationLog;
import com.example.persistance.repository.OperationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OperationLogController {
    @Autowired
    OperationLogRepository repository;

    @RequestMapping("/operlog")
    public String getOperationList(Model model)
    {
        //log.debug("getUserList");
        model.addAttribute(repository.findAll());

        return "operlog";
    }

    @RequestMapping(value = "/operationLogs", method = RequestMethod.GET)
    HttpEntity<PagedResources<OperationLog>> persons(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<OperationLog> operationLogs = repository.findAll(pageable);
        return new ResponseEntity<>(assembler.toResource(operationLogs), HttpStatus.OK);
    }

}
