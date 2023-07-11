package ru.kata.spring.boot_security.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

// API - GET - start
    @GetMapping(value = "api/admin/all")
    public String showAllUsers() throws JsonProcessingException {
        List<User> usersForShow = userService.getAllUsersFromDatabase();
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(usersForShow);
        return userJson;
    }

    @GetMapping(value = "api/admin/new")
    public String returnNewUser() throws JsonProcessingException {
        User user = new User();
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        return userJson;
    }

    @GetMapping("api/admin/{id}")
    public ResponseEntity<User> showUserById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

// API - GET - finish

// API - POST - start
    @PostMapping("api/admin")
    public ResponseEntity<User> createUserApi(@ModelAttribute User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);//201 code - created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500 code
        }
    }
// API - POST - finish

// API - PATCH and DELETE
    @PatchMapping("api/edit/{id}")
    public ResponseEntity<String> updateUserApi(@PathVariable("id") Long id, @ModelAttribute User updatedUser) {
        userService.updateUser(updatedUser);
        return ResponseEntity.ok("{\"message\": \"User updated successfully\"}");
    }

    @DeleteMapping("api/delete/{id}")
    public ResponseEntity<String> deleteUserApi(@PathVariable("id") Long id) {
        // Delete the user with the provided ID
        userService.deleteById(id);
        return ResponseEntity.ok("{\"message\": \"User deleted successfully\"}");
    }

}