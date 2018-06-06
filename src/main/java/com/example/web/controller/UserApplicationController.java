package com.example.web.controller;

import com.example.persistance.entity.UserApplication;
import com.example.web.pojo.UserApplicationPojo;
import com.example.web.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserApplicationController {
    private final UserApplicationService userApplicationService;

    @RequestMapping(value = "/admin/userApplications", method = RequestMethod.GET)
    public ResponseEntity<List<UserApplication>> getUserApplicationList() {
        log.debug("getUserApplicationList");
        return new ResponseEntity<>(userApplicationService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/userApplications/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserApplication> UserApplications(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userApplicationService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/userApplications/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addUserApplication(UserApplicationPojo userApplicationPojo) {
        log.debug("addUserApplication");
        try {
            return new ResponseEntity<>(userApplicationService.addUserApplication(userApplicationPojo), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/userApplications/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteUserApplication(@PathVariable("id") Integer id) {
        log.debug("deleteUserApplication");
        try {
            userApplicationService.deleteUserApplication(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/userApplications/deleteAll", method = RequestMethod.GET)
    public ResponseEntity deleteAllUserApplication() {
        log.debug("deleteAllUserApplications");
        userApplicationService.deleteAllUserApplication();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/admin/userApplications/update/{id}", method = RequestMethod.POST)
    public ResponseEntity updateUserApplication(UserApplicationPojo userApplicationPojo,@PathVariable("id") Integer id) {
        log.debug("updateUserApplication");
        userApplicationService.updateUserApplication(userApplicationPojo, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
