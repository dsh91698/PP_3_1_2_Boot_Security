package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user")
    public String showUser(ModelMap model, Authentication authentication) {
        User user = (User) authentication.getPrincipal(); // Get the authenticated user
        model.addAttribute("user", user);
        return "user_only";
    }

}