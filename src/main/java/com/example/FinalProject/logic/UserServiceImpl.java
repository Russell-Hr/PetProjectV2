package com.example.FinalProject.logic;

import com.example.FinalProject.converter.UserConverter;
import com.example.FinalProject.dao.UserDao;
import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserConverter userConverter;

//    @Autowired
//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    @Transactional
    public void addUser(UserDto userDto) {
        User user = userConverter.asUser(userDto);
        userDao.addUser(user);
    }

    @Override
    @Cacheable(value = "userServiceImpl", key = "#login")
    @Transactional(readOnly = true)
    public UserDto getUser(String login) {
        User user = this.userDao.getUser(login);
        logger.info("User successfully loaded. User details: " + user);
        return userConverter.asUserDto(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
//        Session session = this.sessionFactory.getCurrentSession();
//        List<User> users = session.createQuery("from user").list();
//
//        for(User user: users){
//            logger.info("Book list: " + user);
//        }

        //return users;
        return null;
    }


}
