package com.example.web.service;

import com.example.persistance.entity.User;
import com.example.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;


    @Override
    public User findUserByUsername(String username)
    {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email)
    {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String givenPassword)
    {
        return userRepository.findUserByUsernameAndPassword(username, givenPassword);
    }
}
