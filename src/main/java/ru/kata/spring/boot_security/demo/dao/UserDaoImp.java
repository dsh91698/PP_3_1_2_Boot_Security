package ru.kata.spring.boot_security.demo.dao;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
//@Transactional
@Repository
public class UserDaoImp implements UserDao {
    private final PasswordEncoder passwordEncoder;
    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImp(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        entityManager.persist(user);
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public List<User> selectAllUsersFromDatabase() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public void updateUser(User userUpdated) {
        entityManager.merge(userUpdated);
    }

    @Override
    public void deleteById(Long id) {
        User user = getById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}