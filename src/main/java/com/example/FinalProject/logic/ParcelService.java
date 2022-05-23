package com.example.FinalProject.logic;

import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.dto.ReceiptDto;

import java.sql.Date;
import java.util.List;

public interface ParcelService {
    void addParcel(ParcelDto parcelDto);
    List<ParcelDto> getParcels(String userId, String toPoint, String status,
                               Date createDate, Date paymentDate, Date deliveryDate, int sortColumnNumber);

    void modifyParcel(String id, String status);
    //void modifyParcelReceiptId(int id, long receiptId);
    List<ParcelDto> modifyParcels(List<ParcelDto> parcelsForReceiptDtoList, ReceiptDto receiptDto);
    //@Transactional
    void modifyParcelReceiptId(String id, String receiptId);
}
