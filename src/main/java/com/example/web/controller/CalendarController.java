package com.example.web.controller;

import com.example.web.pojo.CalendarAddVeventReqPojo;
import com.example.web.pojo.CalendarReqPojo;
import com.example.web.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.Description;
import org.osaf.caldav4j.exceptions.CalDAV4JException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.validation.Valid;
import java.util.Calendar;
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

    @RequestMapping(value = "/calendar/timezones", method = RequestMethod.POST)
    public ResponseEntity<List<VTimeZone>> calendarTimeZones(@Valid @RequestBody CalendarReqPojo calendarReqPojo) {

        List<VTimeZone> vTimeZones = calendarService.findAllTimeZones(calendarReqPojo.getCaldavHost(),
                calendarReqPojo.getCaldavPort(),
                calendarReqPojo.getProtocol(),
                calendarReqPojo.getUserName(),
                calendarReqPojo.getPassword(),
                calendarReqPojo.getCalPrefix(),
                calendarReqPojo.getCalPostfix());
        return new ResponseEntity<>(vTimeZones, HttpStatus.OK);
    }


    @RequestMapping(value = "/calendar/addvevent", method = RequestMethod.POST)
    public ResponseEntity<VEvent> calendarAddVevent(@Valid @RequestBody CalendarAddVeventReqPojo calendarAddVeventReqPojo) {

        final java.util.Date startDate = calendarAddVeventReqPojo.getStartDate();

        final VEvent vEvent = new VEvent(new DateTime(startDate.getTime()), new Dur(0,calendarAddVeventReqPojo.getDurationHour(),0,0), calendarAddVeventReqPojo.getEventName());

        vEvent.getProperties().add(new Description(calendarAddVeventReqPojo.getEventDescription()));

        try {

            net.fortuna.ical4j.model.TimeZone fortuneTZ;
            TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();

            String localTimeZone = Calendar.getInstance().getTimeZone().getID();
            fortuneTZ = registry.getTimeZone(localTimeZone);//gets the server time zone.
            VTimeZone tz = fortuneTZ.getVTimeZone();//an iCalendar VTIMEZONE component.


            calendarService.addVevent(calendarAddVeventReqPojo.getCaldavHost(),
                    calendarAddVeventReqPojo.getCaldavPort(),
                    calendarAddVeventReqPojo.getProtocol(),
                    calendarAddVeventReqPojo.getUserName(),
                    calendarAddVeventReqPojo.getPassword(),
                    calendarAddVeventReqPojo.getCalPrefix(),
                    calendarAddVeventReqPojo.getCalPostfix(),
                    vEvent,
                    tz);
                    return new ResponseEntity<>(vEvent, HttpStatus.OK);
        } catch (CalDAV4JException e) {
            e.printStackTrace();
            return new ResponseEntity<>(vEvent, HttpStatus.BAD_REQUEST);
        }

    }

}
