package com.example.FinalProject.converter;

import com.example.FinalProject.dto.ReceiptDto;
import com.example.FinalProject.entity.Receipt;
import org.springframework.stereotype.Component;

@Component
public class ReceiptConverter {

        public Receipt asReceipt(ReceiptDto receiptDto) {
            Receipt receipt = new Receipt();
            receipt.setId(receiptDto.getId());
            receipt.setParcelId(receiptDto.getParcelId());
            receipt.setUserId(receiptDto.getUserId());
            receipt.setManagerId(receiptDto.getManagerId());
            receipt.setStatus(receiptDto.getStatus());
            receipt.setPrice(receiptDto.getPrice());
            receipt.setTotal(receiptDto.getTotal());
            receipt.setCreateDate(receiptDto.getCreateDate());
            receipt.setPaymentDate(receiptDto.getPaymentDate());
            receipt.setInfoUser(receiptDto.getInfoUser());
            receipt.setInfoRoute(receiptDto.getInfoRoute());
            return receipt;
        }

        public ReceiptDto asReceiptDto(Receipt receipt) {
            ReceiptDto receiptDto = new ReceiptDto();
            receiptDto.setId(receipt.getId());
            receiptDto.setParcelId(receipt.getParcelId());
            receiptDto.setUserId(receipt.getUserId());
            receiptDto.setManagerId(receipt.getManagerId());
            receiptDto.setStatus(receipt.getStatus());
            receiptDto.setPrice(receipt.getPrice());
            receiptDto.setTotal(receipt.getTotal());
            receiptDto.setCreateDate(receipt.getCreateDate());
            receiptDto.setPaymentDate(receipt.getPaymentDate());
            receiptDto.setInfoUser(receipt.getInfoUser());
            receiptDto.setInfoRoute(receipt.getInfoRoute());
            return receiptDto;
        }
    }






