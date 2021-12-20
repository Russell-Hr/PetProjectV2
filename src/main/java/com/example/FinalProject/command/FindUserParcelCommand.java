package com.example.FinalProject.command;

import com.example.FinalProject.Constants;
import com.example.FinalProject.DBException;
import com.example.FinalProject.converter.UserConverter;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.logic.ParcelService;
import com.example.FinalProject.logic.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
public class FindUserParcelCommand {
    private static final Logger log = LogManager.getLogger(FindUserParcelCommand.class);
    public String[] cities = Constants.CITIES;

    private ParcelDto parcelDto;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private UserConverter userConverter;

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @GetMapping(value = "/findParcels")

    public String execute (HttpServletRequest req, HttpServletResponse resp) throws DBException, ParseException {
        Validator validator = new Validator();
        String address;
        if (validator.validateRoleAddress(req, resp, req.getParameter("address")).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, req.getParameter("address"));
        }
        int page;
        if (req.getParameter("page") == null) {
            page = 1;
        } else {
            page = Integer.parseInt(req.getParameter("page"));
        }
        int recPerPage = 5;
        List parcels;
        int userId = 0;
        int sortColumnNumber = 0;
        String toPoint = null;
        String status;
        Date createDate = null;
        Date paymentDate = null;
        Date deliveryDate = null;
        if (req.getParameter("sortColumnNumber") != null) {
            sortColumnNumber = Integer.parseInt(req.getParameter("sortColumnNumber"));
        }
        if (req.getParameter("userId") != null) {
            userId = Integer.parseInt(req.getParameter("userId"));
        }
        if (req.getParameter("toPoint") != null && req.getParameter("toPoint") != "") {
            toPoint = req.getParameter("toPoint");
        }
        status = req.getParameter("status");
        if (createDate != null) {
            createDate = Date.valueOf(req.getParameter("createDate"));
        }
        if (paymentDate != null) {
            paymentDate = Date.valueOf(req.getParameter("paymentDate"));
        }
        if (req.getParameter("deliveryDate") != "" && req.getParameter("deliveryDate") != null) {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("deliveryDate"));
            System.out.println(date);
            deliveryDate = new java.sql.Date(date.getTime());
        }
        parcels = parcelService.getParcels(userId, toPoint, status, createDate, paymentDate, deliveryDate, sortColumnNumber);
        int startParcel = page * recPerPage - recPerPage;
        int endParcel = page * recPerPage - 1;
        if (parcels != null) {
            int noOfRecords = parcels.size();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recPerPage);
            req.setAttribute("parcels", parcels);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            req.setAttribute("recordsPerPage", recPerPage);
            req.setAttribute("startParcel", startParcel);
            req.setAttribute("endParcel", endParcel);
            req.setAttribute("sortColumnNumber", sortColumnNumber);
            System.out.println("list parcels != null");
        } else {
            req.removeAttribute("parcels");
        }
        req.setAttribute("cities", cities);
        return address;
    }

}

