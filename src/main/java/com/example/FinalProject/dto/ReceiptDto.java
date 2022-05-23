package com.example.FinalProject.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class ReceiptDto {
    private String id;
    private String parcelId;
    private String userId;
    private String managerId;
    private String status;
    private Double price;
    private Double total;
    private Date createDate;
    private Date paymentDate;
    private String userInfo;
    private String infoRoute;
    private String receiptInfo;
    private List<ParcelDto> parcels = new ArrayList<>();

    public ReceiptDto createReceiptDto(String status) {
        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setStatus(status);
        return receiptDto;
    }
}

