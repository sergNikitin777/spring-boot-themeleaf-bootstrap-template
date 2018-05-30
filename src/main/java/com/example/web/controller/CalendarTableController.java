package com.example.web.controller;

import com.example.persistance.entity.CalendarTable;
import com.example.web.pojo.CalendarTablePojo;
import com.example.web.service.CalendarTableService;
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
public class CalendarTableController {
    private final CalendarTableService calendarTableService;

    @RequestMapping(value = "/admin/calendarTables", method = RequestMethod.GET)
    public ResponseEntity<List<CalendarTable>> getCalendarTableList() {
        log.debug("getCalendarTableList");
        return new ResponseEntity<>(calendarTableService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/calendarTables/{id}", method = RequestMethod.GET)
    public ResponseEntity<CalendarTable> calendarTables(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(calendarTableService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/calendarTables/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addCalendarTable(CalendarTablePojo calendarTable) {
        log.debug("addCalendarTable");
        try {
            return new ResponseEntity<>(calendarTableService.addCalendarTable(calendarTable), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/calendarTables/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteCalendarTable(@PathVariable("id") Integer id) {
        log.debug("deleteCalendarTable");
        try {
            calendarTableService.deleteCalendarTable(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/calendarTables/deleteAll", method = RequestMethod.GET)
    public ResponseEntity deleteAllCalendarTable() {
        log.debug("deleteAllCalendarTable");
        calendarTableService.deleteAllCalendarTable();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/admin/calendarTables/update/{id}", method = RequestMethod.POST)
    public ResponseEntity updateCalendarTable(CalendarTablePojo calendarTable,@PathVariable("id") Integer id) {
        log.debug("updateCalendarTable");
        calendarTableService.updateCalendarTable(calendarTable, id);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
