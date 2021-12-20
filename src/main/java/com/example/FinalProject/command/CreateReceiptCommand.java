package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.converter.ParcelConverter;
import com.example.FinalProject.dao.ParcelDao;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.dto.ReceiptDto;
import com.example.FinalProject.entity.Receipt;
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
    private ParcelConverter parcelConverter;
    @Autowired
    private ParcelDao parcelDao;

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }
    private static final Logger log = LogManager.getLogger(CreateReceiptCommand.class);

    @PostMapping(value = "/createReceipt")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        int userId = 0;
        int sortColumnNumber = 1;
        String toPoint = null;
        String status = "Ordered";
        Date createDate = null;
        Date paymentDate = null;
        Date deliveryDate = null;
        int managerId = 0;
        Validator validator = new Validator();
        String address;
        if (validator.validateRoleAddress(req, resp, req.getParameter("address")).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, req.getParameter("address"));
        }
        if (req.getParameter("userId") != null) {
            userId = Integer.parseInt(req.getParameter("userId"));
        }
        if (req.getParameter("managerId") != null) {
            managerId = Integer.parseInt(req.getParameter("managerId"));
        }
        if (userId != 0 && managerId != 0) {
            //ReceiptManager receiptManager = ReceiptManager.getInstance();
            ReceiptDto receiptDto = new ReceiptDto();
            receiptDto.setUserId(userId);
            receiptDto.setManagerId(managerId);
            receiptDto.setStatus("Ordered");
            receiptDto.setCreateDate(new Date(System.currentTimeMillis()));
            List parcelsDto = parcelService.getParcels(userId, toPoint, status, createDate, paymentDate, deliveryDate, sortColumnNumber);
            //List parcels = null;
            receiptService.addReceipt(receiptDto, parcelsDto);
        }
        return address;
    }
}

