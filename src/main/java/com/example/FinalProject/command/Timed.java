package com.example.FinalProject.command;

import com.example.FinalProject.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

public interface Timed {
    public String execut(HttpServletRequest req, HttpServletResponse resp) throws DBException, ParseException;
}
