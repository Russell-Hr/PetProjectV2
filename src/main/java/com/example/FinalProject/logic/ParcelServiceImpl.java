package com.example.FinalProject.logic;

import com.example.FinalProject.converter.ParcelConverter;
import com.example.FinalProject.dao.ParcelDao;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.entity.Parcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ParcelServiceImpl implements ParcelService {
    private static final Logger logger = LoggerFactory.getLogger(ParcelServiceImpl.class);

    @Autowired
    private ParcelDao parcelDao;
    Parcel parcel;
    @Autowired
    private ParcelConverter parcelConverter;

    @Override
@Transactional
    public void addParcel(ParcelDto parcelDto) {
        boolean res = false;
        Parcel parcel = parcelConverter.asParcel(parcelDto);
        parcelDao.addParcel(parcel);
        logger.info("CREATE PARCEL 2");
    }

    @Override
    public List<ParcelDto> getParcels(int userId, String toPoint, String status,
                                      Date createDate, Date paymentDate, Date deliveryDate, int sortColumnNumber) {
        List<ParcelDto> parcelDtoList = new ArrayList<>();

        if (userId != 0) {
            if (status == null || status.equals("All")) {
                List<Parcel> parcels = parcelDao.getAllByUser(userId);
                for (Parcel parcel :
                        parcels) {
                    parcelDtoList.add(parcelConverter.asParcelDto(parcel));
                }
            } else {
                List<Parcel> parcels = parcelDao.getAllByUserByStatus(userId, status);
                for (Parcel parcel :
                        parcels) {
                    parcelDtoList.add(parcelConverter.asParcelDto(parcel));
                }
            }
        } else {
            if (status == null || status.equals("All")) {

            List<Parcel> parcels = parcelDao.getAll();
            for (Parcel parcel :
                    parcels) {
                parcelDtoList.add(parcelConverter.asParcelDto(parcel));
            }
            } else {
                List<Parcel> parcels = parcelDao.getAllByStatus(status);
                for (Parcel parcel :
                        parcels) {
                    parcelDtoList.add(parcelConverter.asParcelDto(parcel));
                }
            }
        }

        switch (sortColumnNumber) {
            case 0:
                parcelDtoList.sort(Comparator.comparingInt(ParcelDto::getId));
                break;
            case 1:
                parcelDtoList.sort(Comparator.comparingInt(ParcelDto::getUserId));
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
                parcelDtoList.sort(Comparator.comparingInt(ParcelDto::getId));
        }
        return parcelDtoList;
    }

@Override
@Transactional
    public void modifyParcel(int id, String status) {

    parcelDao.modifyParcelStatus(id, status);
    }
}

