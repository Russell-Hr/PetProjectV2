package com.example.FinalProject.logic;

import com.example.FinalProject.converter.UserConverter;
import com.example.FinalProject.dao.UserDao;
import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.entity.Parcel;
import com.example.FinalProject.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserConverter userConverter;

//    @Autowired
//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    @Transactional
    public void addUser(UserDto userDto) {
        User user = userConverter.convert(userDto);
        userDao.addUser(user);
    }

    @Override
    //@Cacheable(value = "userServiceImpl", key = "#login")
    //@Transactional(readOnly = true)
    public UserDto getUser(String login) {
        User user = this.userDao.getUser(login);
        logger.info("User successfully loaded. User details: " + user);
        return userConverter.convert(user);
    }

    @Override
    //@Cacheable(value = "userServiceImpl", key = "#login")
    @Transactional(readOnly = true)
    public UserDto getUserById(String userId) {
        User user = this.userDao.getUserById(userId);
        logger.info("User successfully loaded. User details: " + user);
        return userConverter.convert(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = this.userDao.getAllUsers();
        for (User user : users) {
            userDtoList.add(userConverter.convert(user));
        }
        return userDtoList;
    }

    @Override
    public UserDto create(UserDto dto) {
        return null;
    }

    @Override
    public UserDto get(String passportNumber) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = userDao.getUser(userDto.getLogin());
        User updatedUser = userConverter.convert(userDto);
        performUpdate(user, updatedUser);
        return userConverter.convert(user);
    }

    private void performUpdate(User persistentEntity, User newEntity) {
        //persistentEntity.setName(newEntity.getName());
        //persistentEntity.setPassportNum(newEntity.getPassportNum());

        updateParcels(persistentEntity, newEntity.getParcels());
    }


    private void updateParcels(User persistentEntity, List<Parcel> newParcels) {
        Map<String, Parcel> stillExistentParcels = newParcels
                .stream()
                .filter(p -> Objects.nonNull(p.getId()))
                .collect(Collectors.toMap(Parcel::getId, Function.identity()));

        List<Parcel> parcelsToAdd = newParcels
                .stream()
                //.filter(j -> j.getId() == 0)
                .filter(j -> Objects.isNull(j.getId()))
                .collect(Collectors.toList());

        Iterator<Parcel> persistentIterator = persistentEntity.getParcels().iterator();
        while (persistentIterator.hasNext()) {
            Parcel persistentParcel = persistentIterator.next();
            if (stillExistentParcels.containsKey(persistentParcel.getId())) {
                Parcel updatedParcel = stillExistentParcels.get(persistentParcel.getId());
                updateParcel(persistentParcel, updatedParcel);
            } else { // remove parcels, which do not exist in newParcels collection
                persistentIterator.remove();
                persistentParcel.setUser(null); //  !!!
            }
        }
        for (Parcel parcelToAdd : parcelsToAdd) { // Add on both sides
            persistentEntity.getParcels().add(parcelToAdd);
            parcelToAdd.setUser(persistentEntity);
        }
    }

    private void updateParcel(Parcel persistentParcel, Parcel updatedParcel) {
        persistentParcel.setStatus(updatedParcel.getStatus());
        persistentParcel.setDeliveryDate(updatedParcel.getDeliveryDate());
        persistentParcel.setPaymentDate(updatedParcel.getPaymentDate());
        persistentParcel.setReceipt(updatedParcel.getReceipt());

        //persistentParcel.setDescription(updatedParcel.getDescription());// !!!need to change
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        //userDao.delete(id);
    }


}
