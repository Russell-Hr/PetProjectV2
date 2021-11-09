package com.example.FinalProject.command;

import com.example.FinalProject.entity.Parcel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CalculateParcelCommand {
    private Parcel parcel;

    @Autowired
    public CalculateParcelCommand(Parcel parcel) {
        this.parcel = parcel;
    }

    private static final Logger log = LogManager.getLogger(CalculateParcelCommand.class);

    @GetMapping(value = "/calculate")
    public String calculateParcelCommand
            (HttpServletRequest req,
             @RequestParam("address") String address,
             @RequestParam("fromPoint") String fromPoint,
             @RequestParam("toPoint") String toPoint,
             @RequestParam("length") int length,
             @RequestParam("width") int width,
             @RequestParam("height") int height,
             @RequestParam("weight") double weight
            ) {
        //String address = req.getParameter("address");
        //String fromPoint = req.getParameter("fromPoint");
        //String toPoint = req.getParameter("toPoint");
        //int length = Integer.parseInt(req.getParameter("length"));
        //int width = Integer.parseInt(req.getParameter("width"));
        //int height = Integer.parseInt(req.getParameter("height"));
        //double weight = Double.parseDouble(req.getParameter("weight"));
        int amount = length * width * height;
        //Parcel parcel = new Parcel(fromPoint, toPoint, length, width, height, weight);
        parcel.setFromPoint(fromPoint);
        parcel.setToPoint(toPoint);
        parcel.setLength(length);
        parcel.setWidth(width);
        parcel.setHeight(height);
        parcel.setWeight(weight);
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


