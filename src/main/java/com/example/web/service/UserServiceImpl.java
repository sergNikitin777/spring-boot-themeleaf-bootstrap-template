package com.example.web.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.persistance.enums.Role;
import com.example.web.pojo.UserPojo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.persistance.entity.User;
import com.example.persistance.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Integer addUser(UserPojo user) {
        User newUser= new User(
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getYandexToken(),
                user.getDateYandexToken(),
                user.getSmsToDriver(),
                user.getSmsToClient(),
                user.getNotiftime(),
                user.getRegion(),
                user.getPhone(),
                user.getRequestTransferInterval(),
                user.getCompanyName(),
                Arrays.asList(Role.ROLE_USER)
        );
        return userRepository.save(newUser).getId();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public void deleteAllUser() {
        userRepository.deleteAll();
    }

    @Override
    public void updateUser(UserPojo user, Integer id) {
        if (user != null && id != null) {
            User user1 = userRepository.findOne(id);
            if (user.getEmail() != null) user1.setEmail(user.getEmail());
            if (user.getUsername() != null) user1.setUsername(user.getUsername());
            if (user.getPassword() != null) user1.setPassword(user.getPassword());
            if (user.getYandexToken() != null) user1.setYandexToken(user.getYandexToken());
            //if (user.getDateYandexToken() != null) {//преобразование строки в дату
                //user1.setDateYandexToken(user.getDateYandexToken());
                user1.setDateYandexToken(new Date());
            //}
            if (user.getSmsToDriver() != null) user1.setSmsToDriver(user.getSmsToDriver());
            if (user.getSmsToClient() != null) user1.setSmsToClient(user.getSmsToClient());
            if (user.getNotiftime() != null) user1.setNotiftime(user.getNotiftime());
            if (user.getRegion() != null) user1.setRegion(user.getRegion());
            if (user.getPhone() != null) user1.setPhone(user.getPhone());
            if (user.getRequestTransferInterval() != null)
                user1.setRequestTransferInterval(user.getRequestTransferInterval());
            if (user.getCompanyName()!=null) user1.setCompanyName(user.getCompanyName());
            userRepository.save(user1);
        }
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String givenPassword) {
        return userRepository.findUserByUsernameAndPassword(username, givenPassword);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByYandexToken(String yandexToken) {
        return userRepository.findUserByYandexToken(yandexToken);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id);
    }
}
