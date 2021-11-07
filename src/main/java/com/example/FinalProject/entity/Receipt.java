package com.example.FinalProject.entity;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Receipt {
    private int id;
    private int parcelId;
    private int userId;
    private int managerId;
    private String status;
    private Double price;
    private Double total;
    private Date createDate;
    private Date paymentDate;
    private String infoUser;
    private String infoRoute;

    public static Receipt createReceipt(String status) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContextMVC.xml"
//        );
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
//                SpringConfiguration.class
//        );
        Receipt r = new Receipt();
        //Receipt r = context.getBean(Receipt.class);
        r.setStatus(status);
        return r;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParcelId() {
        return parcelId;
    }

    public void setParcelId(int parcelId) {
        this.parcelId = parcelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public java.sql.Date getCreateDate() {
        return (java.sql.Date) createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getInfoUser() {
        return infoUser;
    }

    public void setInfoUser(String infoUser) {
        this.infoUser = infoUser;
    }

    public String getInfoRoute() {
        return infoRoute;
    }

    public void setInfoRoute(String infoRoute) {
        this.infoRoute = infoRoute;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", parcelId=" + parcelId +
                ", userId=" + userId +
                ", managerId=" + managerId +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", total=" + total +
                ", createDate=" + createDate +
                ", paymentDate=" + paymentDate +
                ", infoUser='" + infoUser + '\'' +
                ", infoRoute='" + infoRoute + '\'' +
                '}';
    }
}
