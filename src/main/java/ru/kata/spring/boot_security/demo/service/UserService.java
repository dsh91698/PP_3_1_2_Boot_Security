package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user); //create

    User getById(Long id); //read

    List<User> getAllUsersFromDatabase(); //read

    void updateUser(User user); //update

    void deleteById(Long id); //delete

}
