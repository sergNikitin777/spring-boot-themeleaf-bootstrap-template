package com.example.web.controller;

import com.example.persistance.entity.Report;
import com.example.web.service.ReportService;
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
public class ReportController {

    private final ReportService reportService;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getReportList() {
        log.debug("getReportList");
        return new ResponseEntity<>(reportService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    public ResponseEntity<Report> getReportById(@PathVariable("id") Integer id) {
        log.debug("getReportById");
        return new ResponseEntity<>(reportService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/report/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addReport(@RequestBody Report report) {
        log.debug("addReport");
        try {
            return new ResponseEntity<>(reportService.addReport(report), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/report/update")
    public ResponseEntity<?> updateReport(@RequestBody Report report) {
        log.debug("updateReport");
        try {
            return new ResponseEntity<>(reportService.updateReport(report), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReport(@PathVariable("id") Integer id) {
        log.debug("deleteReport");
        try {
            reportService.deleteReport(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
