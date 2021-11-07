package com.example.FinalProject.entity;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class User implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;

    public static User createUser(String name, String surname, String login, String password, String role) {
        User user = new User();
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContextMVC.xml"
//        );
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
//                SpringConfiguration.class
//        );
//        User user = context.getBean(User.class);
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        return user;
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
