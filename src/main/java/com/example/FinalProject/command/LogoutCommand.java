package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    private static final Logger log = LogManager.getLogger(LogoutCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String address = "index.jsp";
            req.getSession().invalidate();
        return address;
    }
}

