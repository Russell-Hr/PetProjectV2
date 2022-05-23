package com.example.FinalProject.command;

import com.example.FinalProject.Const;
import com.example.FinalProject.DBException;
import com.example.FinalProject.converter.UserConverterImpl;
import com.example.FinalProject.dao.UserDao;
import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.logic.UserService;
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
    private UserDto userDto;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverterImpl userConverterImpl;
    private UserDao userDao;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/registration")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String address = "main.jsp";
        String name = req.getParameter(Const.NAME);
        String surname = req.getParameter(Const.SURNAME);
        String login = req.getParameter(Const.LOGIN);
        String password = req.getParameter(Const.PASSWORD);
        String password2 = req.getParameter(Const.PASSWORD_2);
        if (password.equals(password2)) {

            UserDto userDto = userService.getUser(login);
            if (userDto != null) {
                address = "error.jsp";
            } else {
                password = passwordEncoder.encode(password);
                userDto = UserDto.createUser(name, surname, login, password, "user");
                userService.addUser(userDto);
            }
        }
        return address;
    }
}
