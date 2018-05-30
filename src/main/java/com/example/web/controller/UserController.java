package com.example.web.controller;

import javax.validation.Valid;

import com.example.web.pojo.UserPojo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.persistance.entity.User;
import com.example.web.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

//    @RequestMapping("/admin/users")
//    public String getUserList(Model model)
//    {
//        log.debug("getUserList");
//        model.addAttribute(userService.findAllUsers());
//
//        return "users";
//    }

//    @GetMapping(value = "/admin/users/add")
//    public String createNewUserForm(Model model)
//    {
//        model.addAttribute("user", new User());
//        return "users-create";
//    }

//    @PostMapping(value = "/admin/users/update")
//    public String createNewUser(@Valid User user, BindingResult bindingResult)
//    {
//        log.debug("createNewUser, username={}, email={}, errorCount={}",
//                user.getUsername(), user.getEmail(), bindingResult.getErrorCount());
//
//        if (userService.findUserByUsername(user.getUsername()) != null)
//        {
//            bindingResult.rejectValue("username", "error.username.exist");
//        }
//
//        if (userService.findUserByEmail(user.getEmail()) != null)
//        {
//            bindingResult.rejectValue("email", "error.email.exist");
//        }
//
//        if (bindingResult.hasErrors())
//        {
//            return "users-create";
//        }
//
//        user.setEnabled(true);
//        userService.saveUser(user);
//
//        return "redirect:/admin/users";
//    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUserList() {
        log.debug("getUserList");
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public ResponseEntity<Integer> addUser(UserPojo user) {
        log.debug("adduser");
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteUser(@PathVariable("id") Integer id) {
        log.debug("deleteUser");
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/admin/users/deleteAll", method = RequestMethod.GET)
    public ResponseEntity deleteAllUser() {
        log.debug("deleteAllUser");
        userService.deleteAllUser();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.POST)
    public ResponseEntity updateUser(UserPojo user, @PathVariable Integer id) {
        log.debug("updateUser");
        userService.updateUser(user,id);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
