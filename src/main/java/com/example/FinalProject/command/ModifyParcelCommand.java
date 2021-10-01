package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.ParcelManager;
import com.example.FinalProject.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyParcelCommand implements Command {
    private static final Logger log = LogManager.getLogger(ModifyParcelCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Validator validator = new Validator();
        String address = null;
        if (validator.validateRoleAddress(req, resp, req.getParameter("address")).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, address);
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

