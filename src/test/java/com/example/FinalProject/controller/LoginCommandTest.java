package com.example.FinalProject.controller;

import com.example.FinalProject.command.LoginCommand;
import com.example.FinalProject.config.TestConfig;
import com.example.FinalProject.entity.User;
import com.example.FinalProject.logic.UserManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class LoginCommandTest {
    @Autowired
    private LoginCommand loginCommand;
    // mocked dependencies
    @Autowired
    private UserManager userManager;

    private static final String login = "password";
    private static final User user = mock(User.class);

    @BeforeAll
    public static void setup() {
        when(user.getLogin()).thenReturn(login);
    }

    @SneakyThrows
    @Test
    public void validateShouldAcceptUserWithAlreadyUsedLogin() {
        when(userManager.getUser(login)).thenReturn(user);
    }

    @SneakyThrows
    @Test
    public void validateShouldRejectUserWithNewLogin() {
        when(userManager.getUser(login)).thenReturn(null);
    }
}
