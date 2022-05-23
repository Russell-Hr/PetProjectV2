package com.example.FinalProject.command;

import com.example.FinalProject.Const;
import com.example.FinalProject.DBException;
import com.example.FinalProject.converter.ParcelConverterImpl;
import com.example.FinalProject.dao.ParcelDao;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.dto.ReceiptDto;
import com.example.FinalProject.logic.ParcelService;
import com.example.FinalProject.logic.ReceiptService;
import com.example.FinalProject.logic.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Controller
public class CreateReceiptCommand {

    @Autowired
    private ParcelDto parcelDto;
    @Autowired
    private ReceiptDto receiptDto;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private ParcelConverterImpl parcelConverterImpl;
    @Autowired
    private ParcelDao parcelDao;

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }
    private static final Logger log = LogManager.getLogger(CreateReceiptCommand.class);

    @PostMapping(value = "/createReceipt")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        String userId = null;
        int sortColumnNumber = 1;
        String toPoint = null;
        String status = Const.ORDERED;
        Date createDate = null;
        Date paymentDate = null;
        Date deliveryDate = null;
        String managerId = null;
        Validator validator = new Validator();
        String address;
        if (validator.validateRoleAddress(req, resp, req.getParameter(Const.ADDRESS)).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, req.getParameter(Const.ADDRESS));
        }
        if (req.getParameter(Const.USER_ID) != null) {
            userId = req.getParameter(Const.USER_ID);
        }
        if (req.getParameter(Const.MANAGER_ID) != null) {
            managerId = req.getParameter(Const.MANAGER_ID);
        }
        if (userId != null && managerId != null) {
            ReceiptDto receiptDto = new ReceiptDto();
            receiptDto.setUserId(userId);
            receiptDto.setManagerId(managerId);
            receiptDto.setStatus(Const.ORDERED);
            receiptDto.setCreateDate(new Date(System.currentTimeMillis()));
            List parcelsDto = parcelService.getParcels(userId, toPoint, status, createDate, paymentDate, deliveryDate, sortColumnNumber);
            //List parcels = null;
            receiptService.addReceipt(receiptDto, parcelsDto);
        }
        return address;
    }
}

