package com.example.web.controller;

import com.example.web.service.YandexCalendarServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fortuna.ical4j.data.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class YandexCalendarController {

    private final YandexCalendarServiceImpl yandexCalendarServiceImpl;

    @RequestMapping(value = "/admin/yandexcalendar/add", method = RequestMethod.POST)
    public void addEventToCalendar(String eventName, String timeZone) throws IOException, ParserException {
        //yandexCalendarServiceImpl.addEventToCalendar(eventName,timeZone);
    }
}
