package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.entity.Parcel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculateParcelCommand implements Command {
    private static final Logger log = LogManager.getLogger(CalculateParcelCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String address = "/get-city-servlet";
        String fromPoint = req.getParameter("fromPoint");
        String toPoint = req.getParameter("toPoint");
        int length = Integer.parseInt(req.getParameter("length"));
        int width = Integer.parseInt(req.getParameter("width"));
        int height = Integer.parseInt(req.getParameter("height"));
        double weight = Double.parseDouble(req.getParameter("weight"));
        int amount = length * width * height;
        Parcel parcel = new Parcel(fromPoint, toPoint, length, width, height, weight);
        int distance = parcel.calculateDistance(fromPoint, toPoint);
        double price = parcel.calculatePrice(distance, amount, weight);
        parcel.setDistance(distance);
        parcel.setPrice(price);
        if (distance != 0) {
            req.getSession().setAttribute("calculatedParcel", parcel);
        } else {
            req.getSession().removeAttribute("calculatedParcel");
        }
        return address;
    }
}


