package com.example.FinalProject.converter;

import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.entity.Parcel;

public interface ParcelConverter {
    Parcel convert(ParcelDto parcelDto);
    ParcelDto convert(Parcel parcel);
}
