package com.example.FinalProject.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;

@Component
@Data
public class ParcelDto implements Serializable, Comparable<ParcelDto> {
    private String id;
    private String fromPoint;
    private String toPoint;
    private String deliveryAddress;
    private String category;
    private int distance;
    private int length;
    private int width;
    private int height;
    private double weight;
    private double price;
    private String status;
    private String userId;
    private String receiptId;
    private Date createDate;
    private Date paymentDate;
    private Date deliveryDate;
    private ReceiptDto receiptDto;

    public ParcelDto() {
    }

    public ParcelDto(String fromPoint, String toPoint, int length, int width, int height, double weight) {
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(ParcelDto parcelDto) {
        return 0;
    }
}




