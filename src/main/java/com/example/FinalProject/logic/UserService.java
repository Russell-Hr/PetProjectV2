package com.example.FinalProject.logic;

import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.entity.User;

import java.util.List;

public interface UserService {

    UserDto getUser(String login);

    void addUser(UserDto userDto);

    List<User> getAllUsers();
}
