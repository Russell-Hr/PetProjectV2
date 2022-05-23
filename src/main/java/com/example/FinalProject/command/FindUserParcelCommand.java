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
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;


@Controller
public class FindUserParcelCommand {
    private static final Logger log = LogManager.getLogger(FindUserParcelCommand.class);
    public String[] cities = Const.CITIES;

    private ParcelDto parcelDto;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private UserConverterImpl userConverterImpl;

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @GetMapping(value = "/findParcels")

    public String execute (HttpServletRequest req, HttpServletResponse resp) throws DBException, ParseException {
        Validator validator = new Validator();
        String address;
        if (validator.validateRoleAddress(req, resp, req.getParameter(Const.ADDRESS)).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, req.getParameter(Const.ADDRESS));
        }
        int page;
        if (req.getParameter(Const.PAGE) == null) {
            page = 1;
        } else {
            page = Integer.parseInt(req.getParameter(Const.PAGE));
        }
        int recPerPage = 5;
        List parcels;
        String userId = null;
        int sortColumnNumber = 0;
        String toPoint = null;
        String status;
        Date createDate = null;
        Date paymentDate = null;
        Date deliveryDate = null;
        if (req.getParameter(Const.SORT_COLUMN_NUMBER) != null) {
            sortColumnNumber = Integer.parseInt(req.getParameter(Const.SORT_COLUMN_NUMBER));
        }
        if (req.getParameter(Const.USER_ID) != null) {
            userId = req.getParameter(Const.USER_ID);
        }
        if (req.getParameter(Const.TO_POINT) != null && req.getParameter(Const.TO_POINT) != "") {
            toPoint = req.getParameter(Const.TO_POINT);
        }
        status = req.getParameter(Const.STATUS);
        if (createDate != null) {
            createDate = Date.valueOf(req.getParameter(Const.CREATE_DATE));
        }
        if (paymentDate != null) {
            paymentDate = Date.valueOf(req.getParameter(Const.PAYMENT_DATE));
        }
        if (req.getParameter(Const.DELIVERY_DATE) != "" && req.getParameter(Const.DELIVERY_DATE) != null) {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter(Const.DELIVERY_DATE));
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

