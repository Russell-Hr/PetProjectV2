package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class LogoutCommand {
    private static final Logger log = LogManager.getLogger(LogoutCommand.class);
    @GetMapping(value = "/logout")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String address = "index.jsp";
            req.getSession().invalidate();
        return address;
    }
}

