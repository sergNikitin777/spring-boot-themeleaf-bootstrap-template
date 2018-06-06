package com.example.web.controller;

import com.example.persistance.entity.Auto;
import com.example.web.pojo.AutoPojo;
import com.example.web.service.AutoService;
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
public class AutoController {
    private final AutoService autoService;

    @RequestMapping(value = "/admin/autos", method = RequestMethod.GET)
    public ResponseEntity<List<Auto>> getAutoList() {
        log.debug("getAutoList");
        return new ResponseEntity<>(autoService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/autos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Auto> autos(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(autoService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/auto/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addAuto(AutoPojo auto) {
        log.debug("addAuto");
        try {
            return new ResponseEntity<>(autoService.addAuto(auto), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/auto/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteAuto(@PathVariable("id") Integer id) {
        log.debug("deleteAuto");
        try {
            autoService.deleteAuto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/auto/deleteAll", method = RequestMethod.GET)
    public ResponseEntity deleteAllAuto() {
        log.debug("deleteAllAuto");
        autoService.deleteAllAuto();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/admin/auto/update/{id}", method = RequestMethod.POST)
    public ResponseEntity updateAuto(AutoPojo auto, @PathVariable Integer id) {
        log.debug("updateAuto");
        autoService.updateAuto(auto,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}