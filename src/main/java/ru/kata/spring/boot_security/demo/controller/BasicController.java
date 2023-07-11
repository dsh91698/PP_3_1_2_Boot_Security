package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BasicController {
    private UserService userService;

    public BasicController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String rootRedirect() {
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
    // ----- user controller ----- //
    @GetMapping("user")
    public String showUser() {
        return "user_only";
    }
    // ----- admin controller --- //
    @GetMapping(value = "/admin")
    public String showAllUsers() {
        return "admin";
    }

}