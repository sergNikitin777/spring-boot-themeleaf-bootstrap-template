package com.example.web.controller;

import com.example.web.pojo.CalendarReqPojo;
import com.example.web.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fortuna.ical4j.model.component.VEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @RequestMapping(value = "/calendar/events", method = RequestMethod.GET)
    public ResponseEntity<List<VEvent>> calendarEvents() {

        String caldavHost = "caldav.yandex.ru";
        Integer caldavPort = 443;
        String protocol = "https";
        String userName = "logistikatest@yandex.ru";
        String password = "kjubcn123456";
        String calPrefix = "/calendars/";
        String calPostfix = "/events-5825759";

        List<VEvent> vEvents = calendarService.findAllEvents(caldavHost, caldavPort, protocol, userName, password, calPrefix, calPostfix);
        return new ResponseEntity<>(vEvents, HttpStatus.OK);
    }


    @RequestMapping(value = "/calendar/eventsbyparam", method = RequestMethod.POST)
    public ResponseEntity<List<VEvent>> calendarEventsByParam(@Valid @RequestBody CalendarReqPojo calendarReqPojo) {

        List<VEvent> vEvents = calendarService.findAllEvents(calendarReqPojo.getCaldavHost(),
                calendarReqPojo.getCaldavPort(),
                calendarReqPojo.getProtocol(),
                calendarReqPojo.getUserName(),
                calendarReqPojo.getPassword(),
                calendarReqPojo.getCalPrefix(),
                calendarReqPojo.getCalPostfix());
        return new ResponseEntity<>(vEvents, HttpStatus.OK);
    }

}
