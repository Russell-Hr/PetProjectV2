package com.example.FinalProject.command;

import com.example.FinalProject.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;

public interface Command {
    String execute (HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException, ParseException;

}
