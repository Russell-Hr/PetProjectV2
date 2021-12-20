package com.example.FinalProject.entity.enums;

public enum UserRole {
    ROLE_ADMINISTRATOR("Admin"),
    ROLE_DISPATCHER("Dispatcher");

    private final String displayValue;

    UserRole(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
