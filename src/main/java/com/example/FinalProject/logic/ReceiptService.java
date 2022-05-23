package com.example.FinalProject.logic;

import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.dto.ReceiptDto;

import java.sql.Date;
import java.util.List;

public interface ReceiptService {
    void addReceipt(ReceiptDto receiptDto, List<ParcelDto> parcelsDto);
    List<ReceiptDto> getReceipts(String id, String userId, String status, Date createDate, Date paymentDate, int sortColumnNumber);
    boolean modifyReceipt (String id, String status);

}
