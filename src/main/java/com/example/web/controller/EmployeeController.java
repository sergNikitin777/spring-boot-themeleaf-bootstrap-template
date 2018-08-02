package com.example.web.controller;

import com.example.persistance.entity.Employee;
import com.example.web.service.EmployeeService;
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
public class EmployeeController {
    private final EmployeeService employeeService;

    @RequestMapping(value = "/admin/employees", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmployeeList() {
        log.debug("getEmployeeList");
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> employees(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/employee/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addEmployee(@RequestBody Employee employee) {
        log.debug("addEmployee");
        try {
            return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/employee/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
        log.debug("deleteEmployee");
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/employee/deleteAll", method = RequestMethod.GET)
    public ResponseEntity<?> deleteAllEmployee() {
        log.debug("deleteAllEmployee");
        employeeService.deleteAllEmployee();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/admin/employee/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        log.debug("updateEmployee");
        try {
            return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
