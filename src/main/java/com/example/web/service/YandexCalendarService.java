package com.example.web.service;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import java.io.IOException;

public interface YandexCalendarService {
    public Calendar loadCalendar() throws IOException, ParserException;
    public void addEventToCalendar(String eventName, String timeZone) throws IOException, ParserException;
}
