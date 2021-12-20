package com.example.FinalProject.dao;


import com.example.FinalProject.entity.Parcel;

import java.util.List;

public interface ParcelDao {

    void addParcel(Parcel parcel);

    void modifyParcelStatus(int id, String status);

    Parcel getParcelById(int id);

    List<Parcel> getAll();

    List<Parcel> getAllByUserByStatus(Integer userId, String status);

    List<Parcel> getAllByStatus(String status);

    List<Parcel> getAllByUser(Integer userId);

    List<Parcel> getAllByReceipt(Integer userId);

}
