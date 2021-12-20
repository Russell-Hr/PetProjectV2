package com.example.FinalProject.logic;

import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.dto.ReceiptDto;

import java.sql.Date;
import java.util.List;

public interface ReceiptService {
    boolean addReceipt(ReceiptDto receiptDto, List<ParcelDto> parcelsDto);
    List<ReceiptDto> getReceipts(int id, int userId, String status, Date createDate, Date paymentDate, int sortColumnNumber);
    boolean modifyReceipt(int id, String status);

}
