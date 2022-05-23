package com.example.FinalProject.dao;


import com.example.FinalProject.entity.Receipt;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReceiptDao {

    //void add(Receipt receipt);

    @Transactional
    void modifyReceiptStatus(String id, String status);

    @Transactional
    void modifyReceiptTotal(String id, Double total);

    List<Receipt> getAll();

    Receipt getById(String receiptId);

    List<Receipt> getAllByUserByStatus(String userId, String status);

    List<Receipt> getAllByStatus(String status);

    List<Receipt> getAllByUser(String userId);

    List<Receipt> getByIds(List<String> ids);

    Receipt addReceipt(Receipt receipt);

    //List<Receipt> getByParcelIds(List<String> receiptIds);
}
