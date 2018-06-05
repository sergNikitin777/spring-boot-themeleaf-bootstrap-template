package com.example.web.service;

import com.example.persistance.entity.CalendarTable;
import com.example.persistance.entity.User;
import com.example.persistance.repository.CalendarTableRepository;
import com.example.persistance.repository.UserRepository;
import com.example.web.pojo.CalendarTablePojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarTableServiceImpl implements CalendarTableService {
    private final CalendarTableRepository calendarTableRepository;
    private final UserRepository userRepository;


    @Override
    public List<CalendarTable> findAll() {
        return calendarTableRepository.findAll();
    }

    @Override
    public CalendarTable findById(Integer id) {
        return calendarTableRepository.findOne(id);
    }

    @Override
    public Integer addCalendarTable(CalendarTablePojo calendarTable) {
        //User user = userRepository.findOne(calendarTable.getUserId());
        return calendarTableRepository.save(new CalendarTable(calendarTable.getAdress(), calendarTable.getToken(), calendarTable.getStatus())).getId();
    }

    @Override
    public void updateCalendarTable(CalendarTablePojo calendarTable, Integer id) {
        if (calendarTable != null && id != null) {
            CalendarTable calendarTable1 = calendarTableRepository.findOne(id);
            if (calendarTable.getAdress() != null) calendarTable1.setAdress(calendarTable.getAdress());
            if (calendarTable.getStatus() != null) calendarTable1.setStatus(calendarTable.getStatus());
            if (calendarTable.getToken() != null) calendarTable1.setToken(calendarTable.getToken());
            calendarTableRepository.save(calendarTable1);
        }
    }

    @Override
    public void deleteCalendarTable(Integer id) {
        calendarTableRepository.delete(id);
    }

    @Override
    public void deleteAllCalendarTable() {
        calendarTableRepository.deleteAll();
    }

}
