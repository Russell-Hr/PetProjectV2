package com.example.FinalProject.command;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectLinkController {
    @GetMapping(value = "/index_jsp")
    public String indexJsp() {
        return "index.jsp";
    }

    @GetMapping(value = "/main_jsp")
    public String mainJsp() {
        return "main.jsp";
    }

    @GetMapping(value = "/login_jsp")
    public String loginJsp() {
        return "login.jsp";
    }

    @GetMapping(value = "/registration_jsp")
    public String registrationJsp() {
        return "registration.jsp";
    }

    @GetMapping(value = "/all_parcel_jsp")
    public String allParcelJsp() { return "all_parcel.jsp"; }

    @GetMapping(value = "/all_parcel_start_jsp")
    public String allParcelStartJsp() { return "all_parcel_start.jsp"; }

    @GetMapping(value = "/all_order_start_jsp")
    public String allOrderStartJsp() { return "all_order_start.jsp"; }

    @GetMapping(value = "/all_receipt_start_jsp")
    public String allReceiptStartJsp() { return "all_receipt_start.jsp"; }

    @GetMapping(value = "/all_delivery_start_jsp")
    public String allDeliveryStartJsp() { return "all_delivery_start.jsp"; }

    @GetMapping(value = "/all_report_start_jsp")
    public String allReportStartJsp() { return "all_report_start.jsp"; }

    @GetMapping(value = "/my_parcel_start_jsp")
    public String myParcelStartJsp() { return "my_parcel_start.jsp"; }

    @GetMapping(value = "/my_receipt_start_jsp")
    public String myReceiptStartJsp() { return "my_receipt_start.jsp"; }

    @GetMapping(value = "/my_create_parcel_jsp")
    public String myCreateParcelJsp() { return "my_create_parcel.jsp"; }

    @GetMapping(value = "/my_create_parcel_start_jsp")
    public String myCreateParcelStartJsp() { return "my_create_parcel_start.jsp"; }

    @GetMapping(value = "/calculate_jsp")
    public String calculateJsp() { return "calculate.jsp"; }

    @GetMapping(value = "/calculate_start_jsp")
    public String calculateStartJsp() { return "calculate_start.jsp"; }

    @GetMapping(value = "/logo.png")
    public String logoPng() { return "logo.png"; }

}
