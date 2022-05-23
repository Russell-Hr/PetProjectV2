package com.example.FinalProject.entity;

public enum Status {
    ORDERED("Ordered"), // when parcel is created
    APPROVED("Approved"), // when parcel is approved
    CANCELED("Canceled"), // when parcel is canceled
    DENIED("Denied"), // when parcel is denied
    PAYED("Payed"), // when parcel is payed
    DELIVERED("Delivered"); // when parcel is delivered

    private String displayValue;

    void ParcelStat(String displayValue) {
        this.displayValue = displayValue;
    }

    Status(String ordered) {
    }

    public String getDisplayValue() {
        return displayValue;
    }




}
