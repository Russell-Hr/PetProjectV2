package com.example.FinalProject.logic;

import com.example.FinalProject.converter.ParcelConverterImpl;
import com.example.FinalProject.converter.UserConverter;
import com.example.FinalProject.dao.ParcelDao;
import com.example.FinalProject.dao.UserDao;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.dto.ReceiptDto;
import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.entity.Parcel;
import com.example.FinalProject.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ParcelServiceImpl implements ParcelService {
    private static final Logger logger = LoggerFactory.getLogger(ParcelServiceImpl.class);

    @Autowired
    private ParcelDao parcelDao;
    @Autowired
    private ParcelConverterImpl parcelConverterImpl;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserService userService;

    User user;
    Parcel parcel;
    UserDto userDto;
    ParcelDto parcelDto;
    ReceiptDto receiptDto;
    List<UserDto> userDtoList;
    List<ParcelDto> parcelDtoList;
    List<Parcel> parcels;
    String parcelStatus;
    Date deliveryDate;

    @Override
    @Transactional
    public void addParcel(ParcelDto parcelDto) {
        //userDto = userConverter.convert(userDao.getUser(parcelDto.getUserId()));
        parcelDtoList = userDto.getParcels();
        parcelDtoList.add(parcelDto);
        //int id = userDto.getId();
        userDto.setParcels(parcelDtoList);
        userService.update(userDto);
    }

    @Override
    public List<ParcelDto> modifyParcels(List<ParcelDto> parcelsForReceiptDtoList, ReceiptDto receiptDto) {
        switch (receiptDto.getStatus()) {
            case "Approved":
                parcelStatus = "Approved";
                parcelsForReceiptDtoList.stream().forEach(p -> p.setReceiptDto(receiptDto));
                break;
            case "Payed":
                parcelStatus = "Payed";
                parcelsForReceiptDtoList.stream().forEach(p -> p.setReceiptDto(receiptDto));
                parcelsForReceiptDtoList.stream().forEach(p -> p.setPaymentDate(new Date(System.currentTimeMillis())));
                break;
            case "Canceled":
            case "Denied":
                parcelStatus = "Ordered";
                //parcelsForReceiptDtoList.stream().forEach(p -> p.setReceiptDto(null));
                parcelsForReceiptDtoList.stream().forEach(p -> p.setReceiptId(null));
                break;
        }
        parcelsForReceiptDtoList.stream().forEach(p -> p.setStatus(parcelStatus));
        //parcelsForReceiptDtoList.stream().forEach(p -> p.setReceiptDto(receiptDto));

        return parcelsForReceiptDtoList;
    }

    @Override
    //@Transactional(readOnly = true)
    public List<ParcelDto> getParcels(String userId, String toPoint, String status,
                                      Date createDate, Date paymentDate, Date deliveryDate, int sortColumnNumber) {
        List<ParcelDto> parcelDtoList;
        if (userId != null) {
            //user = userDao.getUser(userId);
            userDto = userService.getUserById(userId);
            if (status == null || status.equals("All")) {
                //               parcels = parcelDao.getAllByUser(userId);
                parcelDtoList = userDto.getParcels();
            } else {
//                parcels = parcelDao.getAllByUserByStatus(userId, status);
                parcelDtoList = userDto.getParcels().stream().filter(u -> u.getStatus().equals(status)).collect(Collectors.toList());
            }
        } else {
            parcelDtoList = userService.getAllUsers().stream().filter(u -> !u.getRole().equals("manager"))
                    .flatMap(u -> u.getParcels().stream()).collect(Collectors.toList());
            if (status != null && !status.equals("All")) {
                parcelDtoList = parcelDtoList.stream().filter(p -> p.getStatus().equals(status)).collect(Collectors.toList());
            }
        }

        switch (sortColumnNumber) {
            case 0:
                parcelDtoList.sort(Comparator.comparing(ParcelDto::getId));
                break;
            case 1:
                // parcelDtoList.sort(Comparator.comparingInt(ParcelDto::getUserId));
                break;
            case 2:
                parcelDtoList.sort(Comparator.comparing(ParcelDto::getFromPoint));
                break;
            case 3:
                parcelDtoList.sort(Comparator.comparing(ParcelDto::getToPoint));
                break;
            case 4:
                parcelDtoList.sort(Comparator.comparingInt(ParcelDto::getDistance));
                break;
            case 5:
                parcelDtoList.sort(Comparator.comparingDouble(ParcelDto::getWeight));
                break;
            case 6:
                parcelDtoList.sort(Comparator.comparingDouble(ParcelDto::getPrice));
                break;
            case 7:
                parcelDtoList.sort(Comparator.comparing(ParcelDto::getStatus));
                break;
            case 8:
                parcelDtoList.sort(Comparator.comparing(ParcelDto::getCreateDate));
                break;
            default:
                parcelDtoList.sort(Comparator.comparing(ParcelDto::getId));
        }
        return parcelDtoList;
    }

    @Override
    //@Transactional
    public void modifyParcel(String id, String status) {
        if (status.equals("Delivered")) {
            deliveryDate = new Date(System.currentTimeMillis());
            parcelDao.modifyParcelDeliveryDate(id, deliveryDate);
        }
        parcelDao.modifyParcelStatus(id, status);
    }

    @Override
    //@Transactional
    public void modifyParcelReceiptId(String id, String receiptId) {
        //parcelDao.modifyParcelReceiptId(id, status);
        parcelDao.modifyParcelReceiptId(id, receiptId);
    }

}

