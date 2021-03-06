package com.example.FinalProject.config;

import com.example.FinalProject.command.LoginCommand;
import com.example.FinalProject.logic.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {


    @Bean
    public LoginCommand loginCommand() {
        return new LoginCommand();
    }

    @Bean
    public UserServiceImpl userManager() {
        return mock(UserServiceImpl.class);
    }

}
