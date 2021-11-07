package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class LoginCommandTest {

    HttpServletResponse response;
    HttpServletRequest request;
    LoginCommand loginCommand;


    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        loginCommand = new LoginCommand();
    }

    @AfterEach
    void tearDown() {
        request = null;
        response = null;
        loginCommand = null;
    }

    @Test
    void execute() throws Exception {
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        //Assert.assertEquals("main.jsp", loginCommand.execute(request, response));
        verify(request,times(1)).getSession();
        verify(session,times(1)).invalidate();
    }
}