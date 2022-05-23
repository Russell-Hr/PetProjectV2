package com.example.FinalProject.logic;

import com.example.FinalProject.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUser(String login);
    UserDto getUserById(String userId);
    void addUser(UserDto userDto);
    List<UserDto> getAllUsers();
    //=================================================
    UserDto create(UserDto dto);
    UserDto get(String passportNumber);
    UserDto update(UserDto dto);
    void delete(Integer id);
}
