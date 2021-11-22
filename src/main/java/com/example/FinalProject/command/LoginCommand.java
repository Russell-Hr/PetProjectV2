package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.entity.User;
import com.example.FinalProject.logic.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class LoginCommand {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    @GetMapping(value = "/lugin")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String address = "login.jsp";
        Principal principal = req.getUserPrincipal();
        String login = principal.getName();
        UserManager userManager = UserManager.getInstance();
        User user = userManager.getUser(login);
        if (user != null && user.getPassword().equals(user.getPassword())) {
            if (user.getRole().equals("user")) {
                address = "my_parcel_start.jsp";
                req.getSession().setAttribute("role", user.getRole());
            }
            if (user.getRole().equals("manager")) {
                address = "all_parcel_start.jsp";
                req.getSession().setAttribute("role", user.getRole());
            }
            req.getSession().setAttribute("loggedUser", user);
        }
        return address;
    }
}


