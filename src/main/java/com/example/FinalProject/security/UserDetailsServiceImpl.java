package com.example.FinalProject.security;

import com.example.FinalProject.converter.UserConverterImpl;
import com.example.FinalProject.dto.UserDto;
import com.example.FinalProject.entity.User;
import com.example.FinalProject.logic.UserService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LogManager.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    private UserConverterImpl userConverterImpl;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDto userDto = userService.getUser(login);
        assert userDto != null;
        User user = userConverterImpl.convert(userDto);
        log.info("UserDetailsServiceImpl", user.toString());
        return SecurityUser.fromUser(user);
    }
}
