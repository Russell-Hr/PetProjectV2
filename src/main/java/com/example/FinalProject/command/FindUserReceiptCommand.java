package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.converter.UserConverter;
import com.example.FinalProject.dto.ReceiptDto;
import com.example.FinalProject.logic.ParcelService;
import com.example.FinalProject.logic.ReceiptService;
import com.example.FinalProject.logic.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
@Controller
public class FindUserReceiptCommand {
    private static final Logger log = LogManager.getLogger(FindUserReceiptCommand.class);
    private ReceiptDto receiptDto;

    @Autowired
    private ReceiptService receiptService;
    //Autowired
    //private UserConverter userConverter;

    public void setReceiptService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping(value = "/findReceipts")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Validator validator = new Validator();
        String address;
        if (validator.validateRoleAddress(req, resp, req.getParameter("address")).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, req.getParameter("address"));
        }

        int page = 1;
        int recPerPage = 5;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        int id = 0;
        int userId = 0;
        int sortColumnNumber = 0;
        String toPoint = null;
        String status;
        List receipts;
        Date createDate = null;
        Date paymentDate = null;
        Date deliveryDate = null;
        if (req.getParameter("sortColumnNumber") != null) {
            sortColumnNumber = Integer.parseInt(req.getParameter("sortColumnNumber"));
        }
        if (req.getParameter("userId") != null) {
            userId = Integer.parseInt(req.getParameter("userId"));
        }
        if (req.getParameter("toPoint") != null) {
            toPoint = req.getParameter("toPoint");
        }
        status = req.getParameter("status");
        if (createDate != null) {
            createDate = Date.valueOf(req.getParameter("createDate"));
        }
        if (paymentDate != null) {
            paymentDate = Date.valueOf(req.getParameter("paymentDate"));
        }
        receipts = receiptService.getReceipts(id, userId, status, createDate, paymentDate, sortColumnNumber);
        int startReceipt = page * recPerPage - recPerPage;
        int endReceipt = page * recPerPage - 1;
        if (receipts != null) {
            int noOfRecords = receipts.size();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recPerPage);
            System.out.println("Parcel size==>" + noOfRecords);
            System.out.println("Number Of Pages==>" + noOfPages);
            req.setAttribute("receipts", receipts);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            req.setAttribute("recordsPerPage", recPerPage);
            req.setAttribute("startReceipt", startReceipt);
            req.setAttribute("endReceipt", endReceipt);
            req.setAttribute("sortColumnNumber", sortColumnNumber);
        } else {
            req.removeAttribute("receipts");
        }
        return address;
    }
}