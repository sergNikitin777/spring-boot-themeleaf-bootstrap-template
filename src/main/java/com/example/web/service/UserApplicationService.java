package com.example.web.service;

import com.example.persistance.entity.UserApplication;
import com.example.web.pojo.UserApplicationPojo;
import java.util.List;

public interface UserApplicationService {
    List<UserApplication> findAll();

    UserApplication findById(Integer id);

    Integer addUserApplication(UserApplicationPojo calendarTable);

    void updateUserApplication(UserApplicationPojo calendarTable, Integer id);

    void deleteUserApplication(Integer id);

    void deleteAllUserApplication();
}
