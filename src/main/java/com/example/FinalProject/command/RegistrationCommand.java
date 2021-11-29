package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.entity.User;
import com.example.FinalProject.logic.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegistrationCommand {
    private static final Logger log = LogManager.getLogger(RegistrationCommand.class);
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/registration")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String address = "main.jsp";
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        if (password.equals(password2)) {
            password = passwordEncoder.encode(password);
            UserManager userManager = UserManager.getInstance();
            User user = userManager.getUser(login);
            if (user != null) {
                address = "error.jsp";
            } else {
                user = User.createUser(name, surname, login, password, "user");
                userManager.setUser(user);
            }
        }
        return address;
    }
}
