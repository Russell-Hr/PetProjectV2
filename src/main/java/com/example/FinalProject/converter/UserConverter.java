package com.example.FinalProject.converter;


import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto asUserDto(User user) {
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setLogin(user.getLogin());
            userDto.setName(user.getName());
            userDto.setSurname(user.getSurname());
            userDto.setPassword(user.getPassword());
            userDto.setRole(user.getRole());
            return userDto;
        } else {
            return null;
        }
    }

    public User asUser(UserDto userDto) {
        if (userDto != null) {
            User user = new User();
            user.setId(userDto.getId());
            user.setLogin(userDto.getLogin());
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());
            return user;
        } else {
            return null;
        }
    }

}
