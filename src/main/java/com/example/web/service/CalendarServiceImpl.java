package com.example.web.service;

import com.example.persistance.entity.Address;
import com.example.persistance.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {


    @Override
    public List<VEvent> findAllEvents(String caldavHost, Integer caldavPort, String protocol, String username, String password, String calPrefix, String calPostfix) {
        List<VEvent> vEvents = new ArrayList<>();

        try {

            HttpClient httpClient = new HttpClient();
            // I tried it with zimbra - but I had no luck using google calendar
            httpClient.getHostConfiguration().setHost(caldavHost, caldavPort, protocol);

            UsernamePasswordCredentials httpCredentials = new UsernamePasswordCredentials(username, password);
            httpClient.getState().setCredentials(AuthScope.ANY, httpCredentials);
            httpClient.getParams().setAuthenticationPreemptive(true);
            // Need a proxy?
            //httpClient.getHostConfiguration().setProxy("phost", 8080);

            //https://caldav.yandex.ru/calendars/logistikatest%40yandex.ru/events-5825759/

            CalDAVCollection collection = new CalDAVCollection(
                    calPrefix + username + calPostfix,
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vEvents;
    }
}
