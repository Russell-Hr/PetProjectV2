package com.example.FinalProject.command;

import com.example.FinalProject.*;
import com.example.FinalProject.entity.Parcel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class CreateParcelCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateParcelCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Validator validator = new Validator();
        String address = null;
        if (validator.validateRoleAddress(req, resp, req.getParameter("address")).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, address);
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
        int indexFromPoint = 0;
        int indexToPoint = 0;
        double price = 20;
        int amount = length * width * height;
        int distance;
        for (int i = 0; i < Constants.CITIES.length; i++) {
            if (fromPoint.equals(Constants.CITIES[i])) {
                indexFromPoint = i;
            }
            if (toPoint.equals(Constants.CITIES[i])) {
                indexToPoint = i;
            }
        }
        distance = Constants.DISTANCES[indexFromPoint][indexToPoint];
        if (amount > 5000) {
            price = Math.ceil(price + 0.01 * price * amount / 5000);
        }
        if (distance > 300) {
            price = Math.ceil(price + 0.01 * price * amount / 300);
        }
        if (weight > 15) {
            price = Math.ceil(price + 0.01 * price * weight / 15);
        }
        if (distance != 0 && userId != 0 && amount != 0 && weight != 0 && price != 0) {
            ParcelManager parcelManager = ParcelManager.getInstance();
            Parcel parcel = new Parcel(fromPoint, toPoint, distance, length, width, height, weight, price);
            parcel.setDeliveryAddress(deliveryAddress);
            parcel.setCategory(category);
            parcel.setUserId(userId);
            parcel.setCreateDate(new Date(System.currentTimeMillis()));
            //System.out.println("CreateParcelCommand" + parcel.getUserId());
            parcelManager.setParcel(parcel);
        }
        return address;
    }
}

