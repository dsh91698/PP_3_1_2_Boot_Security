package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT DISTINCT user FROM User user LEFT JOIN FETCH user.roles WHERE user.login = :login", User.class
        );
        query.setParameter("login", login);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // User not found
        }
    }

    @Override
    public List<User> getAllUsersFromDatabase() {
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