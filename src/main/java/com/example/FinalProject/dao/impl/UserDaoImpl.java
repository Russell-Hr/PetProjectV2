package com.example.FinalProject.dao.impl;


import com.example.FinalProject.dao.UserDao;
import com.example.FinalProject.entity.User;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(UserDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        LOGGER.info("User has been successfully saved. User details: {}", user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(String login) {
        Session session = sessionFactory.getCurrentSession();
        Object singleResult = session
                .getNamedQuery("userByLogin")
                .setParameter("login", login)
                .uniqueResult();
        User  user = (User) singleResult;
        LOGGER.info("User has been successfully got. User details: {}", user);
        log.info("User has been successfully got. User details: {}", user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(String userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        //Objects.requireNonNull(user, "User not found by id: " + userId);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User").list();

        for (User user :
                users) {
            LOGGER.info("Employee list: {}", user);
        }
        return users;
    }











}
