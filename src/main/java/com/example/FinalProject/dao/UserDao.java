package com.example.FinalProject.dao;


import com.example.FinalProject.entity.User;

import java.util.List;

public interface UserDao {

    User getUser(String login);

    void addUser(User user);

    List<User> getAllUsers();
}
