package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.entity.User;
import com.example.FinalProject.logic.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@EnableAspectJAutoProxy
@Controller
public class SetLanguageCommand {
    private static final Logger log = LogManager.getLogger(SetLanguageCommand.class);
    @Timed
    @GetMapping(value = "/setLang")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String lang = req.getParameter("lang");
        UserManager userManager = UserManager.getInstance();
        List<User> users= userManager.findAllUsers();
        log.info(users);
        String address = "main.jsp";
        req.getSession().setAttribute("lang", lang);
        return address;
    }
}