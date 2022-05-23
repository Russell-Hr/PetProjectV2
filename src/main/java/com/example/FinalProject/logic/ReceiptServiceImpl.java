package com.example.FinalProject.logic;

import com.example.FinalProject.Const;
import com.example.FinalProject.converter.ParcelConverter;
import com.example.FinalProject.converter.ReceiptConverter;
import com.example.FinalProject.dao.ReceiptDao;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.dto.ReceiptDto;
import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.entity.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ReceiptServiceImpl implements ReceiptService {
    private static final Logger logger = LoggerFactory.getLogger(ReceiptServiceImpl.class);

    @Autowired
    private ReceiptDao receiptDao;
    @Autowired
    private UserService userService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private ParcelConverter parcelConverter;
    @Autowired
    private ReceiptConverter receiptConverter;


    UserDto userDto;
    ReceiptDto receiptDto;
    List<ParcelDto> parcelDtoList;
    //List<ReceiptDto> receiptDtoList;

    @Override
    @Transactional
    public void addReceipt(ReceiptDto receiptDto, List<ParcelDto> parcelsForReceiptDtoList) {
        Double total = 0.;
        String status = Const.APPROVED;
//      String receiptInfo = "";
//
//        for (int i = 0; i < parcelsForReceiptDtoList.size(); i++) {
//            receiptInfo = receiptInfo + ("Відправлення № " + parcelsForReceiptDtoList.get(i).getId() + "<br>" +
//                    parcelsForReceiptDtoList.get(i).getFromPoint() + " - " + parcelsForReceiptDtoList.get(i).getToPoint() + "<br>" +
//                    parcelsForReceiptDtoList.get(i).getDeliveryAddress() + "<br>" +
//                    "Ціна: " + parcelsForReceiptDtoList.get(i).getPrice() + " грн." + "<br>");
//        }

        StringBuffer sb = new StringBuffer();
        parcelsForReceiptDtoList.stream().forEach(p -> sb.append(Const.RECEIPT_TOPIC).append(p.getId()).append(Const.BR)
                .append(p.getFromPoint()).append(Const.DASH).append(p.getToPoint()).append(Const.BR)
                .append(p.getDeliveryAddress()).append(Const.BR).append(Const.PRICE)
                .append(p.getPrice()).append(Const.GRN).append(Const.BR));
        String receiptInfo = sb.toString();
        receiptDto.setReceiptInfo(receiptInfo);
        total = parcelsForReceiptDtoList.stream().mapToDouble(p -> p.getPrice()).sum();
        receiptDto.setTotal(total);

        receiptDto.setStatus(status);
        userDto = userService.getUserById(receiptDto.getUserId());
        receiptDto.setParcels(parcelsForReceiptDtoList);
        receiptDto = receiptConverter.convert(receiptDao.addReceipt(receiptConverter.convert(receiptDto)));
        List<ParcelDto> updatedParcelsForReceiptDtoList = parcelService.modifyParcels(parcelsForReceiptDtoList, receiptDto);
        parcelDtoList = userDto.getParcels().stream().filter(p1 -> !(updatedParcelsForReceiptDtoList.stream()
                .anyMatch(p2 -> Objects.equals(p1.getId(), p2.getId())))).collect(Collectors.toList());
        parcelDtoList.addAll(updatedParcelsForReceiptDtoList);
        userDto.setParcels(parcelDtoList);
        userService.update(userDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReceiptDto> getReceipts(String id, String userId, String status, Date createDate, Date paymentDate, int sortColumnNumber) {
        List<ReceiptDto> receiptDtoList;
        if (userId != null) {
            userDto = userService.getUserById(userId);
            if (status == null || status.equals("All")) {
                receiptDtoList = receiptDao.getAllByUser(userId).stream().map(r -> receiptConverter.convert(r)).collect(Collectors.toList());
            } else {
                receiptDtoList = receiptDao.getAllByUserByStatus(userId, status).stream().map(r -> receiptConverter.convert(r)).collect(Collectors.toList());
            }
        } else {
            receiptDtoList = receiptDao.getAll().stream().map(r -> receiptConverter.convert(r)).collect(Collectors.toList());
            if (status != null && !status.equals("All")) {
                receiptDtoList = receiptDtoList.stream().filter(r -> r.getStatus().equals(status)).collect(Collectors.toList());
            }
        }
        return receiptDtoList;
    }

    @Override
    @Transactional
    public boolean modifyReceipt(String id, String status) {
        boolean res = true;
        Receipt receipt = receiptDao.getById(id);
        List<ParcelDto> parcelsForReceiptDtoList = receipt.getParcels().stream().map(p -> parcelConverter.convert(p)).collect(Collectors.toList());
        receiptDto = receiptConverter.convert(receipt);
        //receiptDto.setStatus(status);
        //userDto = userService.getUserById(receiptDto.getUserId());
        //parcelDtoList = receiptDto.getParcels();
        //receiptDto.setStatus(status);
        //parcelDtoList = parcelService.modifyParcels(parcelDtoList, receiptDto);
        //userDto.setParcels(parcelDtoList);
        //userService.update(userDto);
        //==
        receiptDto.setStatus(status);
        userDto = userService.getUserById(receiptDto.getUserId());
        //receiptDto.setParcels(parcelsForReceiptDtoList);

        //receiptDto = receiptConverter.convert(receipt);//?
        List<ParcelDto> updatedParcelsForReceiptDtoList = parcelService.modifyParcels(parcelsForReceiptDtoList, receiptDto);
        receiptDto.setParcels(updatedParcelsForReceiptDtoList);
        receipt=receiptConverter.convert(receiptDto);
        receipt.setStatus("Payed");
        parcelDtoList = userDto.getParcels().stream().filter(p1 -> !(updatedParcelsForReceiptDtoList.stream()
                .anyMatch(p2 -> Objects.equals(p1.getId(), p2.getId())))).collect(Collectors.toList());
        parcelDtoList.addAll(updatedParcelsForReceiptDtoList);
        userDto.setParcels(parcelDtoList);
        userService.update(userDto);
        //==
        return res;
    }
}


