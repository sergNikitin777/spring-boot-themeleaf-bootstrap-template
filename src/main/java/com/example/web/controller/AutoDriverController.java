package com.example.web.controller;

import com.example.persistance.entity.AutoDriver;
import com.example.web.pojo.AutoDriverPojo;
import com.example.web.service.AutoDriverService;
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
public class AutoDriverController {
    private final AutoDriverService autoDriverService;

    @RequestMapping(value = "/admin/auto/drivers", method = RequestMethod.GET)
    public ResponseEntity<List<AutoDriver>> getAutoDriversList() {
        log.debug("getAutoDriversList");
        return new ResponseEntity<>(autoDriverService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/auto/drivers/{id}", method = RequestMethod.GET)
    public ResponseEntity<AutoDriver> getAutoDriver(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(autoDriverService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/auto/driver/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addAutoDriver(AutoDriverPojo autoDriver) {
        log.debug("autoDriver");
        try {
            return new ResponseEntity<>(autoDriverService.addAutoDriver(autoDriver), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/auto/driver/addauto", method = RequestMethod.POST)
    public ResponseEntity<Integer> addAutoDriver(Integer driverId,Integer autoId) {
        log.debug("addAuto");
        try {
            return new ResponseEntity<>(autoDriverService.addAuto(driverId,autoId), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/auto/driver/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteAutoDriver(@PathVariable("id") Integer id) {
        log.debug("deleteAutoDriver");
        try {
            autoDriverService.deleteAutoDriver(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/auto/driver/deleteAll", method = RequestMethod.GET)
    public ResponseEntity deleteAllAutoDriver() {
        log.debug("deleteAllAutoDriver");
        autoDriverService.deleteAllAutoDriver();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/admin/auto/driver/update/{id}", method = RequestMethod.POST)
    public ResponseEntity updateAutoDriver(@PathVariable("id") Integer id) {
        log.debug("updateAutoDriver");

        //

        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

}