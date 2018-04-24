package com.example.web.service;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import org.osaf.caldav4j.exceptions.CalDAV4JException;

import java.util.List;

public interface CalendarService {

    List<VEvent> findAllEvents(String caldavHost, Integer caldavPort, String protocol, String username, String password, String calPrefix, String calPostfix);

    void addVevent(String caldavHost, Integer caldavPort, String protocol, String username, String password, String calPrefix, String calPostfix,
                   VEvent vEvent, VTimeZone vTimeZone) throws CalDAV4JException;
}
