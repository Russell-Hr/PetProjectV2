package com.example.FinalProject.exception;

import com.example.FinalProject.DBException;
import com.example.FinalProject.logic.ParcelServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;

@ControllerAdvice

public class ExceptionResolver {
    private static final Logger log = LogManager.getLogger(ParcelServiceImpl.class);

    @ExceptionHandler({SQLException.class})
    ModelAndView HandleSQLException(SQLException ex) {
        final String modelObject = Arrays.toString(ex.getStackTrace());
        log.error("Error message : {}", modelObject);
        return new ModelAndView("error.jsp", "errorMessage", modelObject);
    }

    @ExceptionHandler({DBException.class})
    ModelAndView HandleDBException(DBException ex) {
        final String modelObject = Arrays.toString(ex.getStackTrace());
        log.error("Error message : {}", modelObject);
        return new ModelAndView("error.jsp", "errorMessage", modelObject);
    }

    @ExceptionHandler({ParseException.class})
    ModelAndView HandleAllException(ParseException ex) {
        final String modelObject = Arrays.toString(ex.getStackTrace());
        log.error("Error message : {}", modelObject);
        return new ModelAndView("error.jsp", "errorMessage", modelObject);
    }

    @ExceptionHandler({IOException.class})
    ModelAndView HandleAllException(IOException ex) {
        final String modelObject = Arrays.toString(ex.getStackTrace());
        log.error("Error message : {}", modelObject);
        return new ModelAndView("error.jsp", "errorMessage", modelObject);
    }

    @ExceptionHandler({ServletException.class})
    ModelAndView HandleAllException(ServletException ex) {
        final String modelObject = Arrays.toString(ex.getStackTrace());
        log.error("Error message : {}", modelObject);
        return new ModelAndView("error.jsp", "errorMessage", modelObject);
    }

}
