package com.example.web.service;

import net.fortuna.ical4j.model.component.VEvent;

import java.util.List;

public interface CalendarService {

    List<VEvent> findAllEvents(String caldavHost, Integer caldavPort, String protocol, String username, String password, String calPrefix, String calPostfix);
}
