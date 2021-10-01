package com.example.FinalProject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validator {
    private static final Logger log = LogManager.getLogger(Validator.class);

    public String validateRoleAddress(HttpServletRequest req, HttpServletResponse resp, String address) {
        if (req.getParameter("address") != null) {
            address = req.getParameter("address");
        } else {
            log.error("Address == null");
            req.getSession().invalidate();
            address = "index.jsp";
            return address;
        }
        if (req.getSession().getAttribute("role") == null) {
            log.info("role == null");
            req.getSession().invalidate();
            address = "index.jsp";
            return address;
        }
        if (!(req.getSession().getAttribute("role").equals("manager")) && address.contains("all_")) {
            log.info("Unathorized entry: page all_...");
            req.getSession().invalidate();
            address = "index.jsp";
            return address;
        }
        if (!(req.getSession().getAttribute("role").equals("user")) && address.contains("my_")) {
            log.info("Unathorized entry: page my_...");
            req.getSession().invalidate();
            address = "index.jsp";
            return address;
        }
        return address;
    }

    public boolean validateCreditCard(String cardNumber, String expireDate) {
        boolean validation = false;
        int[] ints = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            ints[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        if (sum % 10 == 0) {
            validation = true;
        } else {
            validation = false;
        }
        expireDate = expireDate.replace("/", "");
        String now = new SimpleDateFormat("MMyy").format(new Date());
        if (expireDate.compareTo(now) < 0) {
            validation = false;
        }
        return validation;
    }
}

