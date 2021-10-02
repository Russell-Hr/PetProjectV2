package com.example.FinalProject.command;

import com.example.FinalProject.*;
import com.example.FinalProject.entity.Receipt;
import com.example.FinalProject.logic.ParcelManager;
import com.example.FinalProject.logic.ReceiptManager;
import com.example.FinalProject.logic.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class CreateReceiptCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateReceiptCommand.class);
    @Override
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
            ReceiptManager receiptManager = ReceiptManager.getInstance();
            Receipt receipt = new Receipt();
            receipt.setUserId(userId);
            receipt.setManagerId(managerId);
            receipt.setStatus("Ordered");
            receipt.setCreateDate(new Date(System.currentTimeMillis()));
            ParcelManager parcelManager = ParcelManager.getInstance();
            List parcels = parcelManager.findParcelsByUser(userId, toPoint, status, createDate, paymentDate, deliveryDate, sortColumnNumber);
            receiptManager.addReceipt(receipt, parcels);
        }
        return address;
    }
}

