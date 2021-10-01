package com.example.FinalProject;

import com.example.FinalProject.entity.Parcel;
import com.example.FinalProject.entity.Receipt;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class ReceiptManager {

    private DBManager dbManager;

    private static ReceiptManager instance;
    public static synchronized ReceiptManager getInstance() {
        if (instance == null) {
            instance = new ReceiptManager();
        }
        return instance;
    }
    private ReceiptManager() {
        dbManager = DBManager.getInstance();
    }

    public boolean addReceipt(Receipt receipt, List<Parcel> parcels) throws SQLException {
        boolean res = false;
        Double total = 0.;
        String status = "Approved";
        System.out.println("Receipt Manager ==> Add receipt");
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            for (Parcel parcel : parcels) {
                dbManager.modifyParcel(con, parcel.getId(), status);
                total = total + parcel.getPrice();
            }
            receipt.setTotal(total);
            long id = dbManager.addReceipt(con, receipt);
            for (Parcel parcel : parcels) {
                dbManager.addParcelForReceipt(con, (int) id, parcel.getId(), parcel.getPrice());
            }
            con.commit();
            receipt.setId((int) id);
        } catch (SQLException ex) {
            // (1) write to log
            con.rollback();
            // (2)
            new DBException("Cannot add a receipt with id:" + receipt.getId(), ex);
        } finally {
            dbManager.close(con);
        }
        return res;
    }

    public List<Receipt> findReceiptByUser(int id, int userId, String status, Date createDate, Date paymentDate, int sortColumnNumber) throws DBException {
        List<Receipt> receipts = null;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            System.out.println("RM:findReceiptsByUserID:userID==" + userId);
            receipts = dbManager.findReceiptsByUser(con, id, userId, status, createDate, paymentDate);
        } catch (SQLException ex) {
            // (1) write to log
            ex.printStackTrace();
            // log.error("Cannot obtain a p", ex);
            // (2)
            throw new DBException("Cannot get a receipts", ex);
        } finally {
            // Sonar warning
            // create and use separate methods to close each specific type of resource
            dbManager.close(con);
        }
        for (int i = 0; i < receipts.size(); i++) {
            receipts.get(i).setInfoRoute("Відправлення № " + receipts.get(i).getParcelId() + "<br>" + receipts.get(i).getInfoRoute() + "<br>" + "Ціна: " + receipts.get(i).getPrice() + " грн.");
            System.out.println(receipts.get(i));
        }
        int listSize = receipts.size();
        for (int i = 0; i < listSize; i++) {
            while (((i + 1) < listSize) && (receipts.get(i).getId() == receipts.get(i + 1).getId())) {
                receipts.get(i).setInfoRoute(receipts.get(i).getInfoRoute() + "<br><br>" + receipts.get(i + 1).getInfoRoute());
                receipts.remove(i + 1);
                listSize = listSize - 1;
            }
        }
        return receipts;
    }

    public boolean modifyReceipt(int id, String status) throws SQLException {
        List<Receipt> receipts = null;
        boolean res = false;
        Double total = 0.;
        int userId = 0;
        Date createDate = null;
        Date paymentDate = null;
        String statusToFind = null;
        String parcelStatus = null;
        switch (status) {
            case "Payed":
                parcelStatus = "Payed";
                break;
            case "Canceled":
            case "Denied":
                parcelStatus = "Ordered";
                break;
        }
        System.out.println("Receipt Manager ==> Modify receipt");
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            receipts = dbManager.findReceiptsByUser(con, id, userId, statusToFind, createDate, paymentDate);
            dbManager.modifyReceipt(con, id, status);
            for (Receipt receipt : receipts) {
                dbManager.modifyParcel(con, receipt.getParcelId(), parcelStatus);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            // (1) write to log
            con.rollback();
            // (2)
            new DBException("Cannot modify a receipt with id:" + receipts.get(0).getId(), ex);
        } finally {
            dbManager.close(con);
        }
        return res;
    }
}


