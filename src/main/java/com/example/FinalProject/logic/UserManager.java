package com.example.FinalProject.logic;

import com.example.FinalProject.DBException;
import com.example.FinalProject.DBManager;
import com.example.FinalProject.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserManager {
    private DBManager dbManager;
    private static final Logger log = LogManager.getLogger(ParcelManager.class);
    private static UserManager instance;

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    private UserManager() {
        dbManager = DBManager.getInstance();
    }

    public User getUser(String login) throws DBException {
        User user = null;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            user = dbManager.getUser(con, login);
            con.commit();
        } catch (SQLException ex) {
            log.error("cannot do getUser", ex);
            dbManager.rollback(con);
            throw new DBException("Cannot do getUser", ex);
        } finally {
            dbManager.close(con);
        }
        return user;
    }

    public boolean setUser(User user) throws DBException {
        boolean res = false;
        Connection con = null;

        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            long id = dbManager.setUser(con, user);
            con.commit();
            user.setId((int) id);
        } catch (SQLException ex) {
            log.error("cannot do setUser", ex);
            ex.printStackTrace();
            new DBException("Cannot add a user with login:" + user.getLogin(), ex);
        } finally {
            dbManager.close(con);
        }
        return res;
    }

    public List<User> findAllUsers() throws DBException {
        List<User> users = null;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            users = dbManager.findAllUsers(con);
            con.commit();
        } catch (SQLException ex) {
            log.error("cannot do findAllUsers", ex);
            dbManager.rollback(con);
            throw new DBException("Cannot do findAllUsers", ex);
        } finally {
            dbManager.close(con);
        }
        return users;
    }



}
