package ru.kata.spring.boot_security.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String showAllUsers(ModelMap model, Authentication authentication) {
        List<User> usersForShow = userService.getAllUsersFromDatabase();
        model.addAttribute("users", usersForShow);
        User userAuth = (User) authentication.getPrincipal(); // Get the authenticated user
        model.addAttribute("userAuth", userAuth);
        model.addAttribute("userNew", new User()); //tab for NEW user create
        return "admin";
    }

// API - GET - start
    @GetMapping(value = "api/admin/all")
    @ResponseBody
    public String showAllUsers() throws JsonProcessingException {
        List<User> usersForShow = userService.getAllUsersFromDatabase();
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(usersForShow);
        return userJson;
    }

    @GetMapping(value = "api/admin/new")
    @ResponseBody
    public String returnNewUser() throws JsonProcessingException {
        User user = new User();
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        return userJson;
    }

    @GetMapping("api/admin/{id}")
    @ResponseBody
    public ResponseEntity<User> showUserById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("api/admin/{id}/edit")
    @ResponseBody
    public ResponseEntity<User> editUser(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

// API - GET - finish

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

// API - POST - start
    @PostMapping("api/admin")
    public ResponseEntity<User> createUserApi(@RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);//201 code - created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500 code
        }
    }
// API - POST - finish

    @PatchMapping("admin/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }


// API - PATCH and DELETE
    @PatchMapping("api/admin/{id}")
    public ResponseEntity<String> updateUserApi(@PathVariable("id") Long id, @RequestBody User user) {
        // Update the user with the provided ID using the data in the request body
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("api/admin/{id}")
    public ResponseEntity<String> deleteUserApi(@PathVariable("id") Long id) {
        // Delete the user with the provided ID
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}