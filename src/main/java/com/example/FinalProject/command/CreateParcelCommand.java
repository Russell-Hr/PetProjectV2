package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.converter.ParcelConverter;
import com.example.FinalProject.dao.ParcelDao;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.logic.ParcelService;
import com.example.FinalProject.logic.UserService;
import com.example.FinalProject.logic.Validator;
import com.example.FinalProject.service.CalculateParcelParamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

@Controller
public class CreateParcelCommand {
    private ParcelDto parcelDto;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private ParcelConverter parcelConverter;
    private ParcelDao parcelDao;

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }
    private static final Logger log = LogManager.getLogger(CreateParcelCommand.class);
    private CalculateParcelParamService calcParsParamServ;
    @Autowired
    public CreateParcelCommand(CalculateParcelParamService calcParsParamServ) {
        this.calcParsParamServ = calcParsParamServ;
    }

    @GetMapping(value="/createParcel")
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
        ParcelDto parcelDto = new ParcelDto();
        parcelDto.setFromPoint(fromPoint);
        parcelDto.setToPoint(toPoint);
        parcelDto.setLength(length);
        parcelDto.setWidth(width);
        parcelDto.setHeight(height);
        int distance = calcParsParamServ.calculateDistance(fromPoint, toPoint);
        double price = calcParsParamServ.calculatePrice(distance, amount, weight);
        parcelDto.setDistance(distance);
        parcelDto.setPrice(price);
        parcelDto.setDeliveryAddress(deliveryAddress);
        parcelDto.setCategory(category);
        parcelDto.setUserId(userId);
        parcelDto.setStatus("Ordered");
        parcelDto.setCreateDate(new Date(System.currentTimeMillis()));
        log.info("CreateParcelDtoCommand" + parcelDto.getUserId());
        if (distance != 0 && userId != 0 && amount != 0 && weight != 0 && price != 0) {
            parcelService.addParcel(parcelDto);
            log.info("ADD PARCEL");
        }
        return address;
    }
}

