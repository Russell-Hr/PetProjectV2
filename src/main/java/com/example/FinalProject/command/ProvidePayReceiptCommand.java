package com.example.FinalProject.command;

import com.example.FinalProject.Const;
import com.example.FinalProject.DBException;
import com.example.FinalProject.logic.ReceiptService;
import com.example.FinalProject.logic.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
@Controller
public class ProvidePayReceiptCommand {
    private static final Logger log = LogManager.getLogger(ProvidePayReceiptCommand.class);

    @Autowired
    private ReceiptService receiptService;

    @GetMapping(value = "/providePayReceipt")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        Validator validator = new Validator();
        String address = null;
        if (validator.validateRoleAddress(req, resp, req.getParameter(Const.ADDRESS)).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, req.getParameter(Const.ADDRESS));
        }
        String userId = null;
        String receiptId = null;
        Double receiptTotal = 0.;
        String receiptStatus = Const.PAYED;
        String cardNumber = req.getParameter(Const.CARD_NUMBER);
        String expireDate = req.getParameter(Const.EXPIRE_DATA);
        int cvv2 = 0;
        String name = null;
        String surname = null;

        if (req.getParameter(Const.USER_ID) != null) {
            userId = req.getParameter(Const.USER_ID);
        }
        if (req.getParameter(Const.RECEIPT_ID) != null) {
            receiptId = req.getParameter(Const.RECEIPT_ID);
        }
        if (req.getParameter(Const.RECEIPT_TOTAL) != null) {
            receiptTotal = Double.parseDouble(req.getParameter(Const.RECEIPT_TOTAL));
        }
        if (userId != null && receiptId != null && receiptTotal != 0 && validator.validateCreditCard(cardNumber, expireDate)) {
            receiptService.modifyReceipt(receiptId, receiptStatus);
            address = "my_receipt_start.jsp";
        }
        return address;
    }
}