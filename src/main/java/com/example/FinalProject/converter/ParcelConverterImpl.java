package com.example.FinalProject.converter;

import com.example.FinalProject.dao.ReceiptDao;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.entity.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParcelConverterImpl implements ParcelConverter {

    @Autowired
    ReceiptDao receiptDao;

    public Parcel convert(ParcelDto parcelDto) {
        Parcel parcel = new Parcel();
        parcel.setId(parcelDto.getId());
        parcel.setFromPoint(parcelDto.getFromPoint());
        parcel.setToPoint(parcelDto.getToPoint());
        parcel.setDeliveryAddress(parcelDto.getDeliveryAddress());
        parcel.setCategory(parcelDto.getCategory());
        parcel.setDistance(parcelDto.getDistance());
        parcel.setLength(parcelDto.getLength());
        parcel.setWidth(parcelDto.getWidth());
        parcel.setHeight(parcelDto.getHeight());
        parcel.setWeight(parcelDto.getWeight());
        parcel.setPrice(parcelDto.getPrice());
        parcel.setStatus(parcelDto.getStatus());
        parcel.setCreateDate(parcelDto.getCreateDate());
        parcel.setPaymentDate(parcelDto.getPaymentDate());
        parcel.setDeliveryDate(parcelDto.getDeliveryDate());
        if (null != parcelDto.getReceiptDto()) {
            parcel.setReceipt(receiptDao.getById(parcelDto.getReceiptDto().getId()));
        }
        return parcel;
    }

    public ParcelDto convert(Parcel parcel) {
        ParcelDto parcelDto = new ParcelDto();
        parcelDto.setId(parcel.getId());
        parcelDto.setFromPoint(parcel.getFromPoint());
        parcelDto.setToPoint(parcel.getToPoint());
        parcelDto.setDeliveryAddress(parcel.getDeliveryAddress());
        parcelDto.setCategory(parcel.getCategory());
        parcelDto.setDistance(parcel.getDistance());
        parcelDto.setLength(parcel.getLength());
        parcelDto.setWidth(parcel.getWidth());
        parcelDto.setHeight(parcel.getHeight());
        parcelDto.setWeight(parcel.getWeight());
        parcelDto.setPrice(parcel.getPrice());
        parcelDto.setStatus(parcel.getStatus());
        parcelDto.setCreateDate(parcel.getCreateDate());
        parcelDto.setPaymentDate(parcel.getPaymentDate());
        parcelDto.setDeliveryDate(parcel.getDeliveryDate());
        parcelDto.setUserId(parcel.getUser().getId());
        if (null != parcel.getReceipt()) {
            parcelDto.setReceiptId(parcel.getReceipt().getId());
        }
        return parcelDto;
    }
}
