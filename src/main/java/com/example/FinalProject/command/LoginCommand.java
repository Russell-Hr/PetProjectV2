package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.converter.UserConverter;
import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.entity.User;
import com.example.FinalProject.logic.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class LoginCommand {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);
    private UserDto userDto;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/lugin")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String address = "login.jsp";
        Principal principal = req.getUserPrincipal();
        String login = principal.getName();
        UserDto userDto = userService.getUser(login);

        User user = userConverter.convert(userDto);

        if (user != null) {
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


