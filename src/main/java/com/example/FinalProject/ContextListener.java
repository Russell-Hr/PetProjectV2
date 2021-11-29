package com.example.FinalProject;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
@Component
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBManager dbManager = DBManager.getInstance();
        try {
            System.out.println(dbManager.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
