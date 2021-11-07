package com.example.FinalProject.command;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SetLanguageCommand{
    @GetMapping(value = "/setLang")
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String lang = req.getParameter("lang");
        String address = "main.jsp";
        req.getSession().setAttribute("lang", lang);
        return address;
    }
}