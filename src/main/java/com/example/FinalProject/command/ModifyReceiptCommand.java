package com.example.FinalProject.command;

import com.example.FinalProject.DBException;
import com.example.FinalProject.logic.ReceiptManager;
import com.example.FinalProject.logic.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
@Controller
public class ModifyReceiptCommand {
    private static final Logger log = LogManager.getLogger(ModifyReceiptCommand.class);
    @PostMapping(value = "/modifyReceipt")
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        Validator validator = new Validator();
        String address = null;
        if (validator.validateRoleAddress(req, resp, req.getParameter("address")).equals("index.jsp")) {
            return "index.jsp";
        } else {
            address = validator.validateRoleAddress(req, resp, req.getParameter("address"));
        }
        int userId = 0;
        int receiptId = 0;
        Double receiptTotal = 0.;
        String receiptStatus = null;
        String cardNuber = null;
        String expireDate = null;
        int cvv2 = 0;
        String name = null;
        String surname = null;
        if (req.getParameter("userId") != null) {
            userId = Integer.parseInt(req.getParameter("userId"));
        }
        if (req.getParameter("receiptId") != null) {
            receiptId = Integer.parseInt(req.getParameter("receiptId"));
        }
        if (req.getParameter("receiptStatus") != null) {
            receiptStatus = req.getParameter("receiptStatus");
        }
        if (userId != 0 && receiptId != 0 && address != null) {
            ReceiptManager receiptManager = ReceiptManager.getInstance();
            receiptManager.modifyReceipt(receiptId, receiptStatus);
        }
        return address;
    }
}
