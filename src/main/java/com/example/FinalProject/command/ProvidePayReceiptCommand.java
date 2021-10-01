package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.ReceiptManager;
import com.example.FinalProject.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ProvidePayReceiptCommand implements Command {
    private static final Logger log = LogManager.getLogger(ProvidePayReceiptCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        Validator validator = new Validator();
        String address = null;
        if (validator.validateRoleAddress(req, resp, req.getParameter("address")).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, address);
        }
        int userId = 0;
        int receiptId = 0;
        Double receiptTotal = 0.;
        String receiptStatus = "Payed";
        String cardNumber = req.getParameter("cardNumber");
        String expireDate = req.getParameter("expireDate");
        int cvv2 = 0;
        String name = null;
        String surname = null;



        if (req.getParameter("userId") != null) {
            userId = Integer.parseInt(req.getParameter("userId"));
        }
        if (req.getParameter("receiptId") != null) {
            receiptId = Integer.parseInt(req.getParameter("receiptId"));
        }
        if (req.getParameter("receiptTotal") != null) {
            receiptTotal = Double.parseDouble(req.getParameter("receiptTotal"));
        }
        if (userId != 0 && receiptId != 0 && receiptTotal != 0 && validator.validateCreditCard(cardNumber, expireDate)) {
            ReceiptManager receiptManager = ReceiptManager.getInstance();
            receiptManager.modifyReceipt(receiptId, receiptStatus);
            address = "my_receipt_start.jsp";
        }
        return address;
    }
}