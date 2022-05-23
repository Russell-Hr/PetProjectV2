package com.example.FinalProject.converter;

import com.example.FinalProject.dao.ReceiptDao;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.entity.Parcel;
import com.example.FinalProject.entity.Receipt;
import com.example.FinalProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserConverterImpl implements UserConverter {

    @Autowired
    ReceiptDao receiptDao;

    @Autowired
    ParcelConverter parcelConverter;

    public User convert(UserDto userDto) {
        if (userDto != null) {
            User user = new User();
            user.setId(userDto.getId());
            user.setLogin(userDto.getLogin());
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());

            Map<String, Receipt> receiptById = getReceipts(userDto);

            for (ParcelDto parcelDto : userDto.getParcels()) {
                Parcel parcel = parcelConverter.convert(parcelDto);
                parcel.setUser(user);

                Receipt receipt = receiptById.get(parcelDto.getReceiptId());

// //               if (null == receipt) {
// //                   throw new IllegalArgumentException("Receipt with id " + parcelDto.getReceiptId() + " does not exist");
// //               }

                if (null != receipt) {
                    parcel.setReceipt(receipt);
                }

                user.getParcels().add(parcel);
            }
            return user;
        } else {
            return null;
        }
    }

    public Map<String, Receipt> getReceipts(UserDto dto) {
        List<String> receiptIds;
        List<Receipt> receiptList;
        //receiptIds = dto.getParcels().stream().map(ParcelDto::getReceiptId).filter(r -> r != null).collect(Collectors.toList());
        receiptIds = dto.getParcels().stream().map(ParcelDto::getId).filter(r -> r != null).collect(Collectors.toList());
        System.out.println("============" + receiptIds.size());
        receiptList = receiptDao.getByIds(receiptIds);
        //receiptList = receiptDao.getByParcelIds(receiptIds);
        System.out.println("======+++++++++++++++======" + receiptList.size());

        if (null != receiptIds) {
            return receiptList.stream().collect(Collectors.toMap(Receipt::getId, Function.identity()));
        } else {
            return null;
        }
    }

    public UserDto convert(User user) {
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setLogin(user.getLogin());
            userDto.setName(user.getName());
            userDto.setSurname(user.getSurname());
            userDto.setPassword(user.getPassword());
            userDto.setRole(user.getRole());
            List<ParcelDto> parcels = user.getParcels()
                    .stream()
                    .map(parcelConverter::convert)
                   // .sorted(Comparator.comparing(ParcelDto::getReceiptId))
                    .collect(Collectors.toList());
            userDto.setParcels(parcels);
            return userDto;
        } else {
            return null;
        }
    }
}
