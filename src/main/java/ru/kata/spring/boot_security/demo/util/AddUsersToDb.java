package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddUsersToDb {
    private UserService userService;

    @Autowired
    public AddUsersToDb(UserService userService) {
        List<Role> userRole = new ArrayList<>();
        userRole.add(new Role("USER"));

        List<Role> adminRole = new ArrayList<>();
        adminRole.add(new Role("ADMIN"));

        List<Role> adminAndUserRoles = new ArrayList<>();
        adminAndUserRoles.add(new Role("ADMIN"));
        adminAndUserRoles.add(new Role("USER"));

        this.userService = userService;
        userService.addUser(new User("Anand Baulk", "active", 25, "anand","anand", adminAndUserRoles));
        userService.addUser(new User("Caleb Clemens", "active", 15, "user","user", userRole));
        userService.addUser(new User("Noah Newton", "active", 45, "admin","admin", adminRole));
    }

}
