package com.example.FinalProject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "getCityServlet", value = "/get-city-servlet")
public class GetCityServlet extends HttpServlet {
    public String[] cities=Constants.CITIES;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("cities", cities);
        String address = "my_create_parcel.jsp";
        if((request.getParameter("address") != null) && (request.getParameter("address") != address)) {
            address = request.getParameter("address");
        }
        request.getRequestDispatcher(address).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String address = "my_create_parcel.jsp";
        request.setAttribute("cities", cities);
        if((request.getParameter("address") != null) && (request.getParameter("address") != address)) {
            address = request.getParameter("address");
        }
        response.sendRedirect(address);
    }
}