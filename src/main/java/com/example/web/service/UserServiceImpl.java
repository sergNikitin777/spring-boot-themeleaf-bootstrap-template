package com.example.web.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.persistance.entity.auth.Users;
import com.example.persistance.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;

    @Override
    public Users findUserByUsername(String username)
    {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Users findUserByEmail(String email)
    {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<Users> findAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public Users saveUser(Users user)
    {
        return userRepository.save(user);
    }

    @Override
    public Users findUserByUsernameAndPassword(String username, String givenPassword)
    {
        return userRepository.findUserByUsernameAndPassword(username, givenPassword);
    }
}
