package com.example.FinalProject.command;

import com.example.FinalProject.entity.Parcel;
import com.example.FinalProject.service.CalculateParcelParamService;
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
    private CalculateParcelParamService calcParsParamServ;

    @Autowired
    public CalculateParcelCommand(Parcel parcel, CalculateParcelParamService calcParsParamServ) {
        this.parcel = parcel;
        this.calcParsParamServ = calcParsParamServ;
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

        int amount = length * width * height;
        parcel.setFromPoint(fromPoint);
        parcel.setToPoint(toPoint);
        parcel.setLength(length);
        parcel.setWidth(width);
        parcel.setHeight(height);
        parcel.setWeight(weight);
        int distance = calcParsParamServ.calculateDistance(fromPoint, toPoint);
        double price = calcParsParamServ.calculatePrice(distance, amount, weight);
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


