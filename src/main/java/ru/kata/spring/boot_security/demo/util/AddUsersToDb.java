package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Component
public class AddUsersToDb {
    private UserService userService;

    @Autowired
    public AddUsersToDb(UserService userService) {
        this.userService = userService;
        userService.addUser(new User("Anand Baulk", "active", 25));
//        userService.addUser(new User("Caleb Clemens", "active", 35));
//        userService.addUser(new User("Noah Newton", "registered", 15));
    }

}
