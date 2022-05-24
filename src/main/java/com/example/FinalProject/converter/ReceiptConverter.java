package com.example.FinalProject.converter;

import com.example.FinalProject.dto.ReceiptDto;
import com.example.FinalProject.entity.Receipt;

public interface ReceiptConverter {
    Receipt convert(ReceiptDto receiptDto);
    ReceiptDto convert(Receipt receipt);

}
