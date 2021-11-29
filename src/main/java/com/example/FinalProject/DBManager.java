package com.example.FinalProject;

import com.example.FinalProject.entity.Parcel;
import com.example.FinalProject.entity.Receipt;
import com.example.FinalProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.FinalProject.Constants.*;
@Service
public class DBManager {

    private DataSource ds;
    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }
@Autowired
    DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/Shop");
            //System.out.println("Init DBManager");
            //System.out.println(ds.getClass().getName());
        } catch (NamingException ex) {
            throw new IllegalStateException("Cannot init DBManager");
        }
    }

    public Connection getConnection() throws SQLException {
        //System.out.println("GetConnection");
        return ds.getConnection();
    }

    public User getUser(Connection con, String login) throws SQLException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //System.out.println("DBMAnager GetUserStart");
        try {
            pstmt = con.prepareStatement(FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        } finally {
            close(rs);
            close(pstmt);
        }
        return user;
    }

    public void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setRole(rs.getString("role"));
        return user;
    }

    public long setUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, String.valueOf(user.getRole()));
            pstmt.setString(k++, user.getName());
            pstmt.setString(k++, user.getSurname());
            pstmt.setString(k++, user.getLogin());
            pstmt.setString(k++, user.getPassword());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } finally {
            close(rs);
            close(pstmt);
        }
        return 0;
    }

    public int addReceipt(Connection con, Receipt receipt) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(ADD_NEW_RECEIPT, Statement.RETURN_GENERATED_KEYS);
            //userId, managerId, status, total,
            int k = 1;
            pstmt.setInt(k++, receipt.getUserId());
            pstmt.setInt(k++, receipt.getManagerId());
            pstmt.setString(k++, STATUS_NEW_RECEIPT);
            pstmt.setDouble(k++, receipt.getTotal());
            pstmt.setDate(k++, receipt.getCreateDate());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } finally {
            close(rs);
            close(pstmt);
        }
        return 0;
    }

    public void addParcelForReceipt(Connection con, int id, int parcelId, Double parcelPrice) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(ADD_RECEIPT_PARCELS);
            int k = 1;
            pstmt.setInt(k++, id);
            pstmt.setInt(k++, parcelId);
            pstmt.setDouble(k++, parcelPrice);
            pstmt.executeUpdate();
        } finally {
            close(rs);
            close(pstmt);
        }
    }

    public long setParcel(Connection con, Parcel parcel) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(ADD_NEW_PARCEL, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, parcel.getFromPoint());
            pstmt.setString(k++, parcel.getToPoint());
            pstmt.setString(k++, parcel.getDeliveryAddress());
            pstmt.setString(k++, parcel.getCategory());
            pstmt.setInt(k++, parcel.getDistance());
            pstmt.setInt(k++, parcel.getLength());
            pstmt.setInt(k++, parcel.getWidth());
            pstmt.setInt(k++, parcel.getHeight());
            pstmt.setDouble(k++, parcel.getWeight());
            pstmt.setDouble(k++, parcel.getPrice());
            pstmt.setString(k++, STATUS_NEW_PARCEL);
            pstmt.setInt(k++, parcel.getUserId());
            pstmt.setDate(k++, parcel.getCreateDate());
            //System.out.println("pstmt" + pstmt);
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } finally {
            close(rs);
            close(pstmt);
        }
        return 0;
    }

    public boolean modifyParcel(Connection con, int id, String status) throws SQLException {
        boolean res = false;
        PreparedStatement pstmt = null;
        try {
            int k = 1;
            switch (status) {
                case "Ordered":
                case "Approved":
                case "Canceled":
                case "Denied":
                    pstmt = con.prepareStatement(MODIFY_STATUS_PARCEL);
                    pstmt.setString(k++, status);
                    pstmt.setInt(k++, id);
                    break;
                case "Payed":
                    pstmt = con.prepareStatement(MODIFY_STATUS_PAYMENT_DATE_PARCEL);
                    pstmt.setString(k++, status);
                    pstmt.setDate(k++, new Date(System.currentTimeMillis()));
                    pstmt.setInt(k++, id);
                    break;
                case "Delivered":
                    pstmt = con.prepareStatement(MODIFY_STATUS_DELIVERY_DATE_PARCEL);
                    pstmt.setString(k++, status);
                    pstmt.setDate(k++, new Date(System.currentTimeMillis()));
                    pstmt.setInt(k++, id);
                    break;
            }
            //System.out.println("Modify Parcel pstmt===>" + pstmt);
            res = pstmt.executeUpdate() > 0;
        } finally {
            close(pstmt);
        }
        return res;
    }

    public boolean modifyReceipt(Connection con, int id, String status) throws SQLException {
        boolean res = false;
        PreparedStatement pstmt = null;
        try {
            int k = 1;
            switch (status) {
                case "Approved":
                case "Canceled":
                case "Denied":
                    pstmt = con.prepareStatement(MODIFY_STATUS_RECEIPT);
                    pstmt.setString(k++, status);
                    pstmt.setInt(k++, id);
                    break;
                case "Payed":
                    pstmt = con.prepareStatement(MODIFY_STATUS_PAYMENT_DATE_RECEIPT);
                    pstmt.setString(k++, status);
                    pstmt.setDate(k++, new Date(System.currentTimeMillis()));
                    pstmt.setInt(k++, id);
                    break;
            }
            //System.out.println("Modify receipt PSTMT==>" + pstmt);
            res = pstmt.executeUpdate() > 0;
        } finally {
            close(pstmt);
        }
        return res;
    }

    public List<Receipt> findReceiptsByUser(Connection con, int id, int userId, String status, Date createDate, Date paymentDate) throws DBException, SQLException {

        List<Receipt> receipts = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String findReceiptsBy = FIND_RECEIPTS_START;
        if (id != 0 || userId != 0 || status != null || createDate != null || paymentDate != null) {
            findReceiptsBy = findReceiptsBy + " AND ";
        }
        if (id != 0) {
            findReceiptsBy = findReceiptsBy + "receipt.id LIKE ? ESCAPE '!' AND ";
        }
        if (userId != 0) {
            findReceiptsBy = findReceiptsBy + "receipt.userId LIKE ? ESCAPE '!' AND ";
        }
        if ((status != null) && !(status.equals("All"))) {
            findReceiptsBy = findReceiptsBy + "receipt.status LIKE ? ESCAPE '!' AND ";
        }
        if (createDate != null) {
            findReceiptsBy = findReceiptsBy + "receipt.createDate LIKE ? ESCAPE '!' AND ";
        }
        if (paymentDate != null) {
            findReceiptsBy = findReceiptsBy + "receipt.paymentDate LIKE ? ESCAPE '!' AND ";
        }
        if (findReceiptsBy.substring(findReceiptsBy.length() - " AND ".length(), findReceiptsBy.length()).equals(" AND ")) {
            findReceiptsBy = findReceiptsBy.substring(0, findReceiptsBy.length() - " AND ".length());
        }
        try {
            pstmt = con.prepareStatement(findReceiptsBy);
            int k = 1;
            if (id != 0) {
                pstmt.setInt(k++, id);
            }
            if (userId != 0) {
                pstmt.setInt(k++, userId);
            }
            if ((status != null) && !(status.equals("All"))) {
                pstmt.setString(k++, status);
                //System.out.println("status ==>" + status);
            }
            if (createDate != null) {
                pstmt.setDate(k++, createDate);
            }
            if (paymentDate != null) {
                pstmt.setDate(k++, paymentDate);
            }
            //System.out.println("DBM Receipt pstmt " + pstmt);
            //pstmt.setInt(k++, "%" + escapeForLike(userId) + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                receipts.add(mapReceipt(rs));
            }
        } finally {
            close(rs);
            close(pstmt);
        }
        return receipts;
    }

    public List<Parcel> findParcelsByUser(Connection con, int userId, String toPoint, String status, Date createDate, Date paymentDate, Date deliveryDate) throws DBException, SQLException {
        List<Parcel> parcels = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String findParcelsBy = FIND_PARCELS_START;
        //SELECT * FROM parcel WHERE userId LIKE ? ESCAPE '!'
        if (userId != 0 || toPoint != null || status != null || createDate != null || paymentDate != null || deliveryDate != null) {
            findParcelsBy = findParcelsBy + " WHERE ";
        }
        if (userId != 0) {
            findParcelsBy = findParcelsBy + "userId LIKE ? ESCAPE '!' AND ";
        }
        if (toPoint != null) {
            findParcelsBy = findParcelsBy + "toPoint LIKE ? ESCAPE '!' AND ";
        }
        if ((status != null) && !(status.equals("All"))) {
            findParcelsBy = findParcelsBy + "status LIKE ? ESCAPE '!' AND ";
        }
        if (createDate != null) {
            findParcelsBy = findParcelsBy + "createDate LIKE ? ESCAPE '!' AND ";
        }
        if (paymentDate != null) {
            findParcelsBy = findParcelsBy + "paymentDate LIKE ? ESCAPE '!' AND ";
        }
        if (deliveryDate != null) {
            findParcelsBy = findParcelsBy + "deliveryDate LIKE ? ESCAPE '!' AND ";
        }
        if (findParcelsBy.substring(findParcelsBy.length() - " AND ".length(), findParcelsBy.length()).equals(" AND ")) {
            findParcelsBy = findParcelsBy.substring(0, findParcelsBy.length() - " AND ".length());
        }
        try {
            pstmt = con.prepareStatement(findParcelsBy);
            int k = 1;
            if (userId != 0) {
                pstmt.setInt(k++, userId);
            }
            if (toPoint != null) {
                pstmt.setString(k++, toPoint);
            }
            if ((status != null) && !(status.equals("All"))) {
                pstmt.setString(k++, status);
                //System.out.println("status ==>" + status);
            }
            if (createDate != null) {
                pstmt.setDate(k++, createDate);
            }
            if (paymentDate != null) {
                pstmt.setDate(k++, paymentDate);
            }
            if (deliveryDate != null) {
                pstmt.setDate(k++, deliveryDate);
            }
            //System.out.println("DBM Parcel pstmt " + pstmt);
            //pstmt.setInt(k++, "%" + escapeForLike(userId) + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                parcels.add(mapParcel(rs));
            }
        } finally {
            close(rs);
            close(pstmt);
        }
        return parcels;
    }

    //receipt.id, parcel.id, parcel.userId, user.name, user.surname, parcel.fromPoint, parcel.toPoint, parcel.price, receipt.total
    private Receipt mapReceipt(ResultSet rs) throws SQLException {
        Receipt receipt = new Receipt();
        receipt.setId(rs.getInt(RECEIPT_ID));
        receipt.setParcelId(rs.getInt(RECEIPT_PARCEL_ID));
        receipt.setUserId(rs.getInt(RECEIPT_USER_ID));
        receipt.setManagerId(rs.getInt(RECEIPT_MANAGER_ID));
        receipt.setInfoUser(rs.getString(RECEIPT_USER_NAME) + " " + rs.getString(RECEIPT_USER_SURNAME));
        receipt.setInfoRoute(rs.getString(RECEIPT_FROM_POINT) + " - " + rs.getString(RECEIPT_TO_POINT));
        receipt.setPrice(rs.getDouble(RECEIPT_PARCEL_PRICE));
        receipt.setTotal(rs.getDouble(RECEIPT_TOTAL));
        receipt.setStatus(rs.getString(RECEIPT_STATUS));
        receipt.setCreateDate(rs.getDate(RECEIPT_CREATE_DATE));
        receipt.setPaymentDate(rs.getDate(RECEIPT_PAYMENT_DATE));
        return receipt;
    }

    private Parcel mapParcel(ResultSet rs) throws SQLException {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContextMVC.xml"
//        );
//        Parcel parcel = context.getBean(Parcel.class);
        Parcel parcel = new Parcel();
        parcel.setId(rs.getInt(ID));
        parcel.setFromPoint(rs.getString(FROM_POINT));
        parcel.setToPoint(rs.getString(TO_POINT));
        parcel.setDeliveryAddress(rs.getString(DELIVERY_ADDRESS));
        parcel.setCategory(rs.getString(CATEGORY));
        parcel.setDistance(rs.getInt(DISTANCE));
        parcel.setLength(rs.getInt(LENGTH));
        parcel.setWidth(rs.getInt(WIDTH));
        parcel.setHeight(rs.getInt(HEIGHT));
        parcel.setWeight(rs.getDouble(WEIGHT));
        parcel.setPrice(rs.getDouble(PARCEL_PRICE));
        parcel.setStatus(rs.getString(STATUS));
        parcel.setUserId(rs.getInt(USER_ID));
        parcel.setCreateDate(rs.getDate(CREATE_DATE));
        parcel.setPaymentDate(rs.getDate(PAYMENT_DATE));
        parcel.setDeliveryDate(rs.getDate(DELIVERY_DATE));
        return parcel;
    }

    static String escapeForLike(String param) {
        return param.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");
    }

    public List<User> findAllUsers(Connection con) throws SQLException {
        List<User> users = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(FIND_ALL_USERS);
            while (rs.next()) {
                users.add(extractUser(rs));
            }
        } finally {
            close(rs);
            close(stmt);
        }
        return users;
    }
//    public List<Product> findAllProducts() throws SQLException {
//        List<Product> products = new ArrayList<>();
//
//        try (Connection con = getConnection();
//             Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_PRODUCTS);) {
//
//            while (rs.next()) {
//                products.add(mapProduct(rs));
//            }
//        } catch (SQLException ex) {
//            // (1) write to log
//            ex.printStackTrace();
//            // log.error("Cannot obtain a product by login", ex);
//
//            // (2)
//            throw ex;
//        }
//        return products;
//    }



}