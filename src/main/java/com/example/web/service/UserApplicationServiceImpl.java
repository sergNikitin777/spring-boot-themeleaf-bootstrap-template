package com.example.web.service;

import com.example.persistance.entity.UserApplication;
import com.example.persistance.repository.UserApplicationRepository;
import com.example.web.pojo.UserApplicationPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserApplicationRepository userApplicationRepository;

    @Override
    public List<UserApplication> findAll() {
        return userApplicationRepository.findAll();
    }

    @Override
    public UserApplication findById(Integer id) {
        return userApplicationRepository.findOne(id);
    }

    @Override
    public Integer addUserApplication(UserApplicationPojo userApplicationPojo) {
        return userApplicationRepository.save(new UserApplication(userApplicationPojo.getUserId(),
                userApplicationPojo.getAutoId(),
                userApplicationPojo.getAutodriverId(),
                userApplicationPojo.getAdressFrom(),
                userApplicationPojo.getAdressTo(),
                userApplicationPojo.getDate(),
                userApplicationPojo.getPhone(),
                userApplicationPojo.getClientSurname(),
                userApplicationPojo.getClientName(),
                userApplicationPojo.getClientSecondName(),
                userApplicationPojo.getDriverSurname(),
                userApplicationPojo.getDriverName(),
                userApplicationPojo.getDriverSecondName(),
                userApplicationPojo.getSmsToDriver(),
                userApplicationPojo.getSmsToClient(),
                userApplicationPojo.getGoferCount(),
                userApplicationPojo.getPrice(),
                userApplicationPojo.getDescription())).getId();
    }

    @Override
    public void deleteUserApplication(Integer id) {
        userApplicationRepository.delete(id);
    }

    @Override
    public void deleteAllUserApplication() {
        userApplicationRepository.deleteAll();
    }

    @Override
    public void updateUserApplication(UserApplicationPojo userApplicationPojo, Integer id) {
        if (userApplicationPojo != null && id != null) {
            UserApplication userApplication = userApplicationRepository.findOne(id);
            if (userApplicationPojo.getUserId() != null) userApplication.setUserId(userApplicationPojo.getUserId());
            if (userApplicationPojo.getAutoId() != null) userApplication.setAutoId(userApplicationPojo.getAutoId());
            if (userApplicationPojo.getAutodriverId() != null) userApplication.setAutodriverId(userApplicationPojo.getAutodriverId());
            if (userApplicationPojo.getAdressFrom() != null) userApplication.setAdressFrom(userApplicationPojo.getAdressFrom());
            if (userApplicationPojo.getAdressTo() != null) userApplication.setAdressTo(userApplicationPojo.getAdressTo());
            //if (userApplicationPojo.getDate() != null) userApplication.getDate(userApplicationPojo.getDate());//определиться с форматом
            if (userApplicationPojo.getPhone() != null) userApplication.setPhone(userApplicationPojo.getPhone());
            if (userApplicationPojo.getClientSurname() != null) userApplication.setClientSurname(userApplicationPojo.getClientSurname());
            if (userApplicationPojo.getClientName() != null) userApplication.setClientName(userApplicationPojo.getClientName());
            if (userApplicationPojo.getClientSecondName() != null) userApplication.setClientSecondName(userApplicationPojo.getClientSecondName());
            if (userApplicationPojo.getDriverSurname() != null) userApplication.setDriverSurname(userApplicationPojo.getDriverSurname());
            if (userApplicationPojo.getDriverName() != null) userApplication.setDriverName(userApplicationPojo.getDriverName());
            if (userApplicationPojo.getDriverSecondName() != null) userApplication.setDriverSecondName(userApplicationPojo.getDriverSecondName());
            if (userApplicationPojo.getSmsToDriver() != null) userApplication.setSmsToDriver(userApplicationPojo.getSmsToDriver());
            if (userApplicationPojo.getSmsToClient() != null) userApplication.setSmsToClient(userApplicationPojo.getSmsToClient());
            if (userApplicationPojo.getGoferCount() != null) userApplication.setGoferCount(userApplicationPojo.getGoferCount());
            if (userApplicationPojo.getPrice() != null) userApplication.setPrice(userApplicationPojo.getPrice());
            if (userApplicationPojo.getDescription() != null) userApplication.setDescription(userApplicationPojo.getDescription());
            userApplicationRepository.save(userApplication);
        }
    }
}
