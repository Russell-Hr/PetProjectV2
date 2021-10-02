package com.example.FinalProject.logic;

import com.example.FinalProject.DBException;
import com.example.FinalProject.command.Command;
import com.example.FinalProject.command.CommandContainer;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String address = "error.jsp";
		String commandName = req.getParameter("command");
		Command command = CommandContainer.getCommand(commandName);
		try {
			address = command.execute(req, resp);
		} catch (SQLException | DBException | ParseException ex) {
			req.setAttribute("error", ex);
		}
		req.getRequestDispatcher(address).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String address = "error.jsp";
		String commandName = req.getParameter("command");
		Command command = CommandContainer.getCommand(commandName);
		try {
			address = command.execute(req, resp);
		} catch (SQLException | DBException | ParseException ex) {
			req.getSession().setAttribute("error", ex);
		}
		resp.sendRedirect(address);
	}
}
