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
        return calendarTableRepository.save(new CalendarTable(calendarTable.getAdress(), calendarTable.getToken(), calendarTable.getStatus(),calendarTable.getPort(),calendarTable.getUserName(),calendarTable.getPassword(),calendarTable.getPrefix(),calendarTable.getPostfix(),calendarTable.getUserId())).getId();
    }

    @Override
    public void updateCalendarTable(CalendarTablePojo calendarTable, Integer id) {
        if (calendarTable != null && id != null) {
            CalendarTable calendarTable1 = calendarTableRepository.findOne(id);
            if (calendarTable.getAdress() != null) calendarTable1.setAdress(calendarTable.getAdress());
            if (calendarTable.getStatus() != null) calendarTable1.setStatus(calendarTable.getStatus());
            if (calendarTable.getToken() != null) calendarTable1.setToken(calendarTable.getToken());
            if (calendarTable.getPostfix() != null) calendarTable1.setPostfix(calendarTable.getPostfix());
            if (calendarTable.getPrefix() != null) calendarTable1.setPrefix(calendarTable.getPrefix());
            if (calendarTable.getUserName() != null) calendarTable1.setUserName(calendarTable.getUserName());
            if (calendarTable.getPassword() != null) calendarTable1.setPassword(calendarTable.getPassword());
            if (calendarTable.getPort() != null) calendarTable1.setPort(calendarTable.getPort());
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
