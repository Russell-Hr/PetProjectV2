package com.example.FinalProject.command;

import com.example.FinalProject.Const;
import com.example.FinalProject.DBException;
import com.example.FinalProject.converter.UserConverterImpl;
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
import java.sql.SQLException;
@Controller
public class ModifyReceiptCommand {
    private static final Logger log = LogManager.getLogger(ModifyReceiptCommand.class);

    private ReceiptDto receiptDto;
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private UserConverterImpl userConverterImpl;

    public void setReceiptService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping(value = "/modifyReceipt")
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
        String receiptStatus = null;
        String cardNuber = null;
        String expireDate = null;
        int cvv2 = 0;
        String name = null;
        String surname = null;
        if (req.getParameter(Const.USER_ID) != null) {
            userId = req.getParameter(Const.USER_ID);
        }
        if (req.getParameter(Const.RECEIPT_ID) != null) {
            receiptId = req.getParameter(Const.RECEIPT_ID);
        }
        if (req.getParameter(Const.RECEIPT_STATUS) != null) {
            receiptStatus = req.getParameter(Const.RECEIPT_STATUS);
        }
        if (userId != null && receiptId != null && address != null) {
            receiptService.modifyReceipt(receiptId, receiptStatus);
        }
        return address;
    }
}
