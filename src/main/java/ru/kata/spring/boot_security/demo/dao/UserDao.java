package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    //CRUD
    void addUser(User user); //create

    User getById(Long id); //read

    User findByLogin(String login);

    List<User> getAllUsersFromDatabase(); //read

    void updateUser(User user); //update

    void deleteById(Long id); //delete
}
