package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AddUsersToDb {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AddUsersToDb(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        //create roleList and fill in Roles-table in DB
        List<Role> roleList = new ArrayList<>(Arrays.asList(new Role("ROLE_USER"), new Role("ROLE_ADMIN")));
        roleService.addRolesToDb(roleList);

        List<Role> userRole = Arrays.asList(roleService.getRoleByName("ROLE_USER"));

        List<Role> adminRole = Arrays.asList(roleService.getRoleByName("ROLE_ADMIN"));

        List<Role> adminAndUserRoles = Arrays.asList(roleService.getRoleByName("ROLE_USER"), roleService.getRoleByName("ROLE_ADMIN"));

        userService.addUser(new User("Anand Baulk", "active", 25, "anand", "anand", adminAndUserRoles));
        userService.addUser(new User("Caleb Clemens", "active", 15, "user", "user", userRole));
        userService.addUser(new User("Noah Newton", "active", 45, "admin", "admin", adminRole));
    }

}
