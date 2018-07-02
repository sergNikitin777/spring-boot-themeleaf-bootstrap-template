package com.example.web.service;

import java.util.List;

import com.example.persistance.entity.auth.Users;

public interface UserService
{

    Users findUserByUsername(String username);

    Users findUserByEmail(String email);

    List<Users> findAllUsers();

    Users saveUser(Users user);

    Users findUserByUsernameAndPassword(String username, String givenPassword);

}
