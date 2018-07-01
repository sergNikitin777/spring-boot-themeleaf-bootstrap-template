package com.example.web.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.persistance.entity.User;
import com.example.persistance.repository.UserRepository;

import lombok.RequiredArgsConstructor;

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

    @PreAuthorize("hasAuthority('ROLE_Z')") // hasAuthority('ROLE_X') - Forbidden
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
