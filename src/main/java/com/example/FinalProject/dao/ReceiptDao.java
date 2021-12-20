package com.example.FinalProject.dao;


import com.example.FinalProject.entity.Receipt;

import java.util.List;

public interface ReceiptDao {

    //void add(Receipt receipt);

    List<Receipt> getAll();

    List<Receipt> getAllByUserByStatus(Integer userId, String status);

    List<Receipt> getAllByUser(Integer userId);

    long addReceipt(Receipt receipt);
}
