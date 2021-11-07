package com.example.FinalProject.command;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.*;

class LogoutCommandTest {

    HttpServletResponse response;
    HttpServletRequest request;
    LogoutCommand logoutCommand;

    @BeforeEach
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        logoutCommand = new LogoutCommand();
    }

    @AfterEach
    public void tearDown() throws Exception {
        request = null;
        response = null;
        logoutCommand = null;
    }

    @Test
    void execute() throws Exception {
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        Assert.assertEquals("main.jsp", logoutCommand.execute(request, response));
        verify(request,times(1)).getSession();
        verify(session,times(1)).invalidate();
    }
}