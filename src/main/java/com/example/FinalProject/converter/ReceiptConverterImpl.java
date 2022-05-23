package com.example.FinalProject.converter;

import com.example.FinalProject.Const;
import com.example.FinalProject.dao.UserDao;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.dto.ReceiptDto;
import com.example.FinalProject.entity.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceiptConverterImpl implements ReceiptConverter {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ParcelConverter parcelConverter;

    public Receipt convert(ReceiptDto receiptDto) {
        Receipt receipt = new Receipt();
        receipt.setId(receiptDto.getId());
        receipt.setUser(userDao.getUserById(receiptDto.getUserId()));
        receipt.setManagerId(receiptDto.getManagerId());
        receipt.setStatus(receiptDto.getStatus());
        receipt.setTotal(receiptDto.getTotal());
        receipt.setCreateDate(receiptDto.getCreateDate());
        receipt.setReceiptInfo(receiptDto.getReceiptInfo());
        return receipt;
    }

    public ReceiptDto convert(Receipt receipt) {
        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setId(receipt.getId());
        receiptDto.setUserId(receipt.getUser().getId());
        receiptDto.setManagerId(receipt.getManagerId());
        receiptDto.setStatus(receipt.getStatus());
        receiptDto.setTotal(receipt.getTotal());
        receiptDto.setCreateDate(receipt.getCreateDate());
        receiptDto.setReceiptInfo(receipt.getReceiptInfo());
        receiptDto.setUserInfo(receipt.getUser().getName() + Const.BR + receipt.getUser().getSurname());
        List<ParcelDto> parcels = receipt.getParcels()
                .stream()
                .map(parcelConverter::convert)
                .sorted(Comparator.comparing(ParcelDto::getReceiptId))
                .collect(Collectors.toList());
        receiptDto.setParcels(parcels);
        return receiptDto;
    }
}






