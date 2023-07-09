package ru.kata.spring.boot_security.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
        model.addAttribute("userAuth", user);
        return "user_only";
    }

    @GetMapping("api/user")
    @ResponseBody
    public String showUser(Authentication authentication) throws JsonProcessingException {
        User user = (User) authentication.getPrincipal(); // Get the authenticated user
        // Create an ObjectMapper to convert the User object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        return userJson;
    }

}