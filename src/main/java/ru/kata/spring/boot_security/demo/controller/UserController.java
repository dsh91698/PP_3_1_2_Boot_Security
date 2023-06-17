package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/")
    public String rootRedirect() {
        return "redirect:/users";
    }

    @GetMapping(value = "/users")
    public String showAllUsers(ModelMap model) {
        List<User> usersForShow = userService.selectAllUsersFromDatabase();
        model.addAttribute("users", usersForShow);
        return "users";
    }

    @GetMapping("users/{id}")
    public String showUserById(@PathVariable("id") Long id, ModelMap model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("users/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("users/{id}/edit")
    public String editUser(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PatchMapping("users/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }



}