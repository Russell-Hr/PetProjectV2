package com.example.FinalProject.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class UserDto implements Serializable {
    private String id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;
    private List<ParcelDto> parcels = new ArrayList<>();
    //private List<ReceiptDto> receipts = new ArrayList<>();

    public static UserDto createUser (String name, String surname, String login, String password, String role) {
        UserDto userDto = new UserDto();
        userDto.setName(name);
        userDto.setSurname(surname);
        userDto.setLogin(login);
        userDto.setPassword(password);
        userDto.setRole(role);
        return userDto;
    }
}
