package com.example.FinalProject.dao;


import com.example.FinalProject.entity.Parcel;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface ParcelDao {

    void addParcel(Parcel parcel);

    void modifyParcelStatus(String id, String status);

    Parcel getParcelById(int id);

    List<Parcel> getAll();

    List<Parcel> getAllByUserByStatus(Integer userId, String status);

    List<Parcel> getAllByStatus(String status);

    List<Parcel> getAllByUser(String userId);

    List<Parcel> getAllByReceipt(Integer userId);

    void modifyParcelReceiptId(String id, String receiptId);

    void modifyParcelDeliveryDate(String id, Date deliveryDate);

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    List<Parcel> getAllByUserByReceiptId(Integer userId, Integer receiptId);

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    List<Parcel> getAllByUserWithReceipt(Integer userId);

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    List<Parcel> getAllWithReceipt();


}
