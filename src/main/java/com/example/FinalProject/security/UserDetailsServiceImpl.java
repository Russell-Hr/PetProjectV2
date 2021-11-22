package com.example.FinalProject.security;

import com.example.FinalProject.entity.User;
import com.example.FinalProject.logic.UserManager;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserManager userManager = UserManager.getInstance();
        User user = userManager.getUser(login);
        return SecurityUser.fromUser(user);
    }
}
