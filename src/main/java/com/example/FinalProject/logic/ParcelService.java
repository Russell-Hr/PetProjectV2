package com.example.FinalProject.logic;

import com.example.FinalProject.dto.ParcelDto;

import java.sql.Date;
import java.util.List;

public interface ParcelService {
    void addParcel(ParcelDto parcelDto);
    List<ParcelDto> getParcels(int userId, String toPoint, String status,
                               Date createDate, Date paymentDate, Date deliveryDate, int sortColumnNumber);

    void modifyParcel(int id, String status);

}
