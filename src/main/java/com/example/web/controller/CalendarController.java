package com.example.web.controller;

import com.example.persistance.entity.Address;
import com.example.web.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.component.VEvent;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.osaf.caldav4j.CalDAVCollection;
import org.osaf.caldav4j.CalDAVConstants;
import org.osaf.caldav4j.methods.CalDAV4JMethodFactory;
import org.osaf.caldav4j.methods.HttpClient;
import org.osaf.caldav4j.model.request.CalendarQuery;
import org.osaf.caldav4j.util.GenerateQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CalendarController {

    private final AddressService addressService;


    @RequestMapping(value = "/calendar/events", method = RequestMethod.GET)
    public ResponseEntity<List<VEvent>> calendarEvents() {
        //List<Address> addresses = addressService.findAll();

        List<VEvent> vEvents = new ArrayList<>();

        try {

            HttpClient httpClient = new HttpClient();
            // I tried it with zimbra - but I had no luck using google calendar
            httpClient.getHostConfiguration().setHost("caldav.yandex.ru", 443, "https");
            String username = "logistikatest@yandex.ru";
            UsernamePasswordCredentials httpCredentials = new UsernamePasswordCredentials(username, "kjubcn123456");
            httpClient.getState().setCredentials(AuthScope.ANY, httpCredentials);
            httpClient.getParams().setAuthenticationPreemptive(true);
            // Need a proxy?
            //httpClient.getHostConfiguration().setProxy("phost", 8080);

            //https://caldav.yandex.ru/calendars/logistikatest%40yandex.ru/events-5825759/

            CalDAVCollection collection = new CalDAVCollection(
                    "/calendars/" + username + "/events-5825759",
                    (HostConfiguration) httpClient.getHostConfiguration().clone(),
                    new CalDAV4JMethodFactory(),
                    CalDAVConstants.PROC_ID_DEFAULT
            );

            GenerateQuery gq = new GenerateQuery();
            // TODO you might want to adjust the date
            //gq.setFilter("VEVENT [20131001T000000Z;20131010T000000Z] : STATUS!=CANCELLED");
            // Get the raw caldav query
            // System.out.println("Query: "+ gq.prettyPrint());
            CalendarQuery calendarQuery = gq.generate();
            List<Calendar> calendars = collection.queryCalendars(httpClient, calendarQuery);

            for (Calendar calendar : calendars) {
                ComponentList componentList = calendar.getComponents().getComponents(Component.VEVENT);
                Iterator<VEvent> eventIterator = componentList.iterator();
                while (eventIterator.hasNext()) {
                    VEvent ve = eventIterator.next();

                    vEvents.add(ve);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(vEvents, HttpStatus.OK);
    }
}
