package com.example.FinalProject.command;

import com.example.FinalProject.Const;
import com.example.FinalProject.DBException;
import com.example.FinalProject.converter.UserConverterImpl;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.logic.ParcelService;
import com.example.FinalProject.logic.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class ModifyParcelCommand {
    private static final Logger log = LogManager.getLogger(ModifyParcelCommand.class);
    private ParcelDto parcelDto;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private UserConverterImpl userConverterImpl;

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }


    @PostMapping(value = "/modifyParcel")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Validator validator = new Validator();
        String address;
        if (validator.validateRoleAddress(req, resp, req.getParameter(Const.ADDRESS)).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, req.getParameter(Const.ADDRESS));
        }
        String toCity = null;
        String status = req.getParameter(Const.STATUS);
        String id = req.getParameter(Const.PARCEL_ID);
        if (req.getParameter(Const.TO_CITY) != null) {
            toCity = req.getParameter(Const.TO_CITY);
        }
        if (id != null && status != null) {
            parcelService.modifyParcel(id, status);
        }
        req.setAttribute(Const.TO_CITY, toCity);
        return address;
    }
}

