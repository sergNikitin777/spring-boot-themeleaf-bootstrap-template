package com.example.web.service;

import com.example.persistance.entity.CalendarTable;
import com.example.web.pojo.CalendarTablePojo;

import java.util.List;

public interface CalendarTableService {
    List<CalendarTable> findAll();

    CalendarTable findById(Integer id);

    Integer addCalendarTable(CalendarTablePojo calendarTable);

    void updateCalendarTable(CalendarTablePojo calendarTable, Integer id);

    void deleteCalendarTable(Integer id);

    void deleteAllCalendarTable();
}
