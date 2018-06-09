package com.example.web.service;

import lombok.RequiredArgsConstructor;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.SimpleHostInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.UUID;

import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.DateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class YandexCalendarServiceImpl implements YandexCalendarService{

    @Override
    public Calendar loadCalendar() throws IOException, ParserException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("calendar.ics");
        CalendarBuilder builder = new CalendarBuilder();
        return builder.build(is);
    }

    @Override
    public void addEventToCalendar(String eventName, String timeZone) throws IOException, ParserException {
        // Create a TimeZone
        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
        TimeZone timezone = registry.getTimeZone(timeZone);
        VTimeZone tz = timezone.getVTimeZone();
        // Start Date is on: April 1, 2008, 9:00 am
        java.util.Calendar startDate = new GregorianCalendar();
        startDate.setTimeZone(timezone);
        startDate.set(java.util.Calendar.MONTH, java.util.Calendar.APRIL);
        startDate.set(java.util.Calendar.DAY_OF_MONTH, 1);
        startDate.set(java.util.Calendar.YEAR, 2008);
        startDate.set(java.util.Calendar.HOUR_OF_DAY, 9);
        startDate.set(java.util.Calendar.MINUTE, 0);
        startDate.set(java.util.Calendar.SECOND, 0);
        // End Date is on: April 1, 2008, 13:00
        java.util.Calendar endDate = new GregorianCalendar();
        endDate.setTimeZone(timezone);
        endDate.set(java.util.Calendar.MONTH, java.util.Calendar.APRIL);
        endDate.set(java.util.Calendar.DAY_OF_MONTH, 1);
        endDate.set(java.util.Calendar.YEAR, 2008);
        endDate.set(java.util.Calendar.HOUR_OF_DAY, 13);
        endDate.set(java.util.Calendar.MINUTE, 0);
        endDate.set(java.util.Calendar.SECOND, 0);
        // Create the event
        DateTime start = new DateTime(startDate.getTime());
        DateTime end = new DateTime(endDate.getTime());
        VEvent meeting = new VEvent(start, end, eventName);
        // add timezone info..
        meeting.getProperties().add(tz.getTimeZoneId());
        // generate unique identifier..
        String uidValue = UUID.randomUUID() + "@" + new SimpleHostInfo("yandex.ru");
        Uid uid = new Uid(uidValue);
        meeting.getProperties().add(uid);
        Calendar calendar = loadCalendar();
        calendar.getComponents().add(meeting);
        System.out.println(calendar);
    }
}
