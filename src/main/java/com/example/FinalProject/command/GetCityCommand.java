package com.example.FinalProject.command;

import com.example.FinalProject.Const;
import com.example.FinalProject.DBException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin(methods={RequestMethod.GET})
public class GetCityCommand {
    public String[] cities= Const.CITIES;
    @GetMapping(value = "/getCity")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        req.setAttribute("cities", cities);
        String address = req.getParameter("address");
        return address;
    }
}