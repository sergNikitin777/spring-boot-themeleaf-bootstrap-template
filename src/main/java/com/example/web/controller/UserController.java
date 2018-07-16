package com.example.web.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.persistance.entity.auth.Users;
import com.example.web.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @RequestMapping("/admin/users")
    public String getUserList(Model model)
    {
        log.debug("getUserList");
        //UserDetails userDetails = getUserDetails(); -- отладка
        //Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        model.addAttribute(userService.findAllUsers());

        return "users";
    }
    
    protected UserDetails getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
          userDetails = (UserDetails) principal;
        }
        return userDetails;
      }

    @GetMapping(value = "/admin/users/create")
    public String createNewUserForm(Model model)
    {
        model.addAttribute("user", new Users());
        return "users-create";
    }

    @PostMapping(value = "/admin/users/create")
    public String createNewUser(@Valid Users user, BindingResult bindingResult)
    {
        log.debug("createNewUser, username={}, email={}, errorCount={}",
                user.getUsername(), user.getEmail(), bindingResult.getErrorCount());

        if (userService.findUserByUsername(user.getUsername()) != null)
        {
            bindingResult.rejectValue("username", "error.username.exist");
        }

        if (userService.findUserByEmail(user.getEmail()) != null)
        {
            bindingResult.rejectValue("email", "error.email.exist");
        }

        if (bindingResult.hasErrors())
        {
            return "users-create";
        }

        user.setIsblocked(0);
        userService.saveUser(user);

        return "redirect:/admin/users";
    }

}
