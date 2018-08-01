package com.example.web.controller;

import com.example.persistance.entity.Ekt;
import com.example.web.service.EktService;
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
public class EktController {

    private final EktService ektService;

    @RequestMapping(value = "/ekt", method = RequestMethod.GET)
    public ResponseEntity<List<Ekt>> getEktList() {
        log.debug("getEktList");
        return new ResponseEntity<>(ektService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/ekt/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ekt> getEktById(@PathVariable("id") Integer id) {
        log.debug("getEktById");
        return new ResponseEntity<>(ektService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/ekt/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addEkt(Ekt ekt) {
        log.debug("addEkt");
        try {
            return new ResponseEntity<>(ektService.addEkt(ekt), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/ekt/update")
    public ResponseEntity<?> updateEkt(@RequestBody Ekt ekt) {
        log.debug("updateEkt");
        try {
            return new ResponseEntity<>(ektService.updateEkt(ekt), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/ekt/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEkt(@PathVariable("id") Integer id) {
        log.debug("deleteEkt");
        try {
            ektService.deleteEkt(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
