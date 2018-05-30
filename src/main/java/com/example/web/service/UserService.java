package com.example.web.service;

import java.util.List;

import com.example.persistance.entity.User;
import com.example.web.pojo.UserPojo;

public interface UserService
{

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    List<User> findAll();

    User saveUser(User user);

    Integer addUser(UserPojo user);

    void deleteUser(Integer id);

    void deleteAllUser();

    void updateUser(UserPojo user, Integer id);

    User findUserByUsernameAndPassword(String username, String givenPassword);

}
