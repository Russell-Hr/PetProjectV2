package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.logic.ParcelManager;
import com.example.FinalProject.logic.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class ModifyParcelCommand {
    private static final Logger log = LogManager.getLogger(ModifyParcelCommand.class);
    @PostMapping(value = "/modifyParcel")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Validator validator = new Validator();
        String address;
        if (validator.validateRoleAddress(req, resp, req.getParameter("address")).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, req.getParameter("address"));
        }
        String toCity = null;
        String status = req.getParameter("status");
        int id = Integer.parseInt(req.getParameter("parcel_id"));
        if (req.getParameter("toCity") != null) {
            toCity = req.getParameter("toCity");
        }
        if (id != 0 && status != null) {
            ParcelManager parcelManager = ParcelManager.getInstance();
            parcelManager.modifyParcel(id, status);
        }
        req.setAttribute("toCity", toCity);
        return address;
    }
}

