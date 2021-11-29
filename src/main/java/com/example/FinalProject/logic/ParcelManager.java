package com.example.FinalProject.logic;

import com.example.FinalProject.DBException;
import com.example.FinalProject.DBManager;
import com.example.FinalProject.entity.Parcel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Service
public class ParcelManager {
    private DBManager dbManager;
    private static final Logger log = LogManager.getLogger(ParcelManager.class);
    private static ParcelManager instance;
    public static synchronized ParcelManager getInstance() {
        if (instance == null) {
            instance = new ParcelManager();
        }
        return instance;
    }
    public ParcelManager() {
        dbManager = DBManager.getInstance();
    }

    public boolean setParcel(Parcel parcel) throws DBException {
        boolean res = false;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            long id = dbManager.setParcel(con, parcel);
            con.commit();
            parcel.setId((int) id);
        } catch (SQLException ex) {
            log.error("cannot do setParcel", ex);
            ex.printStackTrace();
            new DBException("Cannot add a parcel with id:" + parcel.getId(), ex);
        } finally {
            dbManager.close(con);
        }
        return res;
    }

    public List<Parcel> findParcelsByUser(int userId, String toPoint, String status, Date createDate, Date paymentDate, Date deliveryDate, int sortColumnNumber) throws DBException {
        List<Parcel> parcels = null;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            log.info("findParcelsByUser"+ userId);
            parcels = dbManager.findParcelsByUser(con, userId, toPoint, status, createDate, paymentDate, deliveryDate);
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error("cannot do findParcelsByUser", ex);
            throw new DBException("Cannot get a parcels by user", ex);
        } finally {
            dbManager.close(con);
        }
        switch (sortColumnNumber) {
            case 0:
                parcels.sort(Comparator.comparingInt(Parcel::getId));
                break;
            case 1:
                parcels.sort(Comparator.comparingInt(Parcel::getUserId));
                break;
            case 2:
                parcels.sort(Comparator.comparing(Parcel::getFromPoint));
                break;
            case 3:
                parcels.sort(Comparator.comparing(Parcel::getToPoint));
                break;
            case 4:
                parcels.sort(Comparator.comparingInt(Parcel::getDistance));
                break;
            case 5:
                parcels.sort(Comparator.comparingDouble(Parcel::getWeight));
                break;
            case 6:
                parcels.sort(Comparator.comparingDouble(Parcel::getPrice));
                break;
            case 7:
                parcels.sort(Comparator.comparing(Parcel::getStatus));
                break;
            case 8:
                parcels.sort(Comparator.comparing(Parcel::getCreateDate));
                break;
            default:
                parcels.sort(Comparator.comparingInt(Parcel::getId));
        }
        return parcels;
    }


    public boolean modifyParcel(int id, String status) {
        boolean res = false;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            dbManager.modifyParcel(con, id, status);
            con.commit();
        } catch (SQLException ex) {
            log.error("cannot do modifyParcel", ex);
            ex.printStackTrace();
            new DBException("Cannot modify status of parcel with id:" + id, ex);
        } finally {
            dbManager.close(con);
        }
        return res;
    }
}

