package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        if (user.getRoles() == null) { // USER role set by default
            user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        }//TODO:
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDao.addUser(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> selectAllUsersFromDatabase() {
        return userDao.selectAllUsersFromDatabase();
    }

    @Override
    public void updateUser(User user) {
        User userFromDB = userDao.getById(user.getId());

        System.out.println("userFromDB.getPassword() ->" + userFromDB.getPassword());
        System.out.println("user.getPassword() ->" + user.getPassword());
        System.out.println("user ->" + user);

        user.setRoles(new ArrayList<>(userFromDB.getRoles()));//TODO:

        boolean hasNewPassword =  user.getPassword() != "";// is there new password in input?
        if (hasNewPassword) {// if something was typed in password field - encode password and add into updated user
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
        else { // if there was no user input in password field - extract old password from DB and add into updated user
            user.setPassword(userFromDB.getPassword()); // Set the original password if it hasn't changed
        }
        userDao.updateUser(user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

}