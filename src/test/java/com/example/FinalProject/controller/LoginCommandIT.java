package com.example.FinalProject.controller;

import com.example.FinalProject.command.configuration.SpringConfiguration;
import com.example.FinalProject.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        SpringConfiguration.class, SecurityConfig.class
})
@WebAppConfiguration
public class LoginCommandIT {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void homeShouldRedirectToUsersPage() throws Exception {
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/lugin");
    ResultActions result = mvc.perform(request);
        result.andExpect(MockMvcResultMatchers.view().name("my_parcel_start.jsp"));
    }
}
