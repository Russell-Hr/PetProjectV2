package com.example.FinalProject.command;

import com.example.FinalProject.*;
import com.example.FinalProject.entity.Parcel;
import com.example.FinalProject.logic.ParcelManager;
import com.example.FinalProject.logic.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
@Controller
public class CreateParcelCommand {
    private static final Logger log = LogManager.getLogger(CreateParcelCommand.class);

    @PostMapping(value="/createParcel")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Validator validator = new Validator();
        String address;
        if (validator.validateRoleAddress(req, resp, req.getParameter("address")).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, req.getParameter("address"));
        }
        String fromPoint = req.getParameter("fromPoint");
        String toPoint = req.getParameter("toPoint");
        String deliveryAddress = "вул. " + req.getParameter("street") + " " +
                req.getParameter("house") + "/" + req.getParameter("flat") + " \n" +
                req.getParameter("surnameR") + " " + req.getParameter("nameR");
        String category = req.getParameter("category");
        int length = Integer.parseInt(req.getParameter("length"));
        int width = Integer.parseInt(req.getParameter("width"));
        int height = Integer.parseInt(req.getParameter("height"));
        double weight = Double.parseDouble(req.getParameter("weight"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        int amount = length * width * height;
        Parcel parcel = new Parcel(fromPoint, toPoint, length, width, height, weight);
        int distance = parcel.calculateDistance(fromPoint, toPoint);
        double price = parcel.calculatePrice(distance, amount, weight);
        parcel.setDistance(distance);
        parcel.setPrice(price);
        parcel.setDeliveryAddress(deliveryAddress);
        parcel.setCategory(category);
        parcel.setUserId(userId);
        parcel.setCreateDate(new Date(System.currentTimeMillis()));
        System.out.println("CreateParcelCommand" + parcel.getUserId());
        if (distance != 0 && userId != 0 && amount != 0 && weight != 0 && price != 0) {
            ParcelManager parcelManager = ParcelManager.getInstance();
            parcelManager.setParcel(parcel);
        }
        return address;
    }
}

