package com.example.FinalProject;

import com.example.FinalProject.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserManager {

    private DBManager dbManager;

    ////////////////////////////////

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

    ////////////////////////////////

    public User getUser(String login) throws DBException {
        User user = null;
        Connection con = null;
        System.out.println("Im here");
        try {
            con = dbManager.getConnection();
            user = dbManager.getUser(con, login);
            con.commit();
            System.out.println("commit");
        } catch (SQLException ex) {
            // (1) write to log
            // LOG.error("cannot do getUser", ex);

            // (2)
            dbManager.rollback(con);

            // (3)
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
            // (1) write to log
            ex.printStackTrace();

            // (2)
            new DBException("Cannot add a user with login:" + user.getLogin(), ex);
        } finally {
            dbManager.close(con);
        }
        return res;
    }



/////////////////////////////////////

}
