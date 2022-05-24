package com.example.FinalProject.converter;

import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.entity.Receipt;
import com.example.FinalProject.entity.User;

import java.util.Map;

public interface UserConverter {
    User convert(UserDto userDto);
    Map<String, Receipt> getReceipts(UserDto dto);
    UserDto convert(User user);

}
