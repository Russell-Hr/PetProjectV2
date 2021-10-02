package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.entity.User;
import com.example.FinalProject.logic.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    private static final Logger log = LogManager.getLogger(RegistrationCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String address = "main.jsp";
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserManager userManager = UserManager.getInstance();
        User user = userManager.getUser(login);
        if (user != null && user.getPassword().equals(password)) {
            address = "error.jsp";
        } else {
            user = User.createUser(name, surname, login, password, "user");
            userManager.setUser(user);
        }
        return address;
    }
}
