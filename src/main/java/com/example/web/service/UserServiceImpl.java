package com.example.web.service;

import java.util.Arrays;
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
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public Integer addUser(UserPojo user) {
        return userRepository.save(new User(
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
        )).getId();
    }

    @Override
    public void deleteUser(Integer id) { userRepository.delete(id); }

    @Override
    public void deleteAllUser() { userRepository.deleteAll(); }

    @Override
    public void updateUser(UserPojo user, Integer id) {
        User user1 = userRepository.findOne(id);
        user1.setEmail(user.getEmail());
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setYandexToken(user.getYandexToken());
        user1.setDateYandexToken(user.getDateYandexToken());
        user1.setSmsToDriver(user.getSmsToDriver());
        user1.setSmsToClient(user.getSmsToClient());
        user1.setNotiftime(user.getNotiftime());
        user1.setRegion(user.getRegion());
        user1.setPhone(user.getPhone());
        user1.setRequestTransferInterval(user.getRequestTransferInterval());
        user1.setCompanyName(user.getCompanyName());
        userRepository.save(user1);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String givenPassword)
    {
        return userRepository.findUserByUsernameAndPassword(username, givenPassword);
    }

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
}
