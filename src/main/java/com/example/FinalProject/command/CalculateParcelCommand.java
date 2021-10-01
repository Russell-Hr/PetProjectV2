package com.example.FinalProject.command;
import com.example.FinalProject.Constants;
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
        int indexFromPoint = 0;
        int indexToPoint = 0;
        double weight = Double.parseDouble(req.getParameter("weight"));
        int amount = length * width * height;
        int distance;
        double price = 20;
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
            price = Math.ceil(price + 0.01 * price * distance / 300);
        }
        if (weight > 15) {
            price = Math.ceil(price + 0.01 * price * weight / 15);
        }
        Parcel parcel = new Parcel(fromPoint, toPoint, distance, length, width, height, weight, price);
        if (indexFromPoint != 0 && indexToPoint != 0) {
            req.getSession().setAttribute("calculatedParcel", parcel);
        } else {
            req.getSession().removeAttribute("calculatedParcel");
        }
        return address;
    }
}


