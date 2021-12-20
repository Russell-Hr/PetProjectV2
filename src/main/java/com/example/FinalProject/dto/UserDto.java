package com.example.FinalProject.dto;

import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Component
public class UserDto implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;

    public static UserDto createUser (String name, String surname, String login, String password, String role) {
        UserDto userDto = new UserDto();
        userDto.setName(name);
        userDto.setSurname(surname);
        userDto.setLogin(login);
        userDto.setPassword(password);
        userDto.setRole(role);
        return userDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                "}";
    }
}
