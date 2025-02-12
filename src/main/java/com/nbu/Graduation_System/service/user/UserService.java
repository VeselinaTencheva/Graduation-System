package com.nbu.Graduation_System.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nbu.Graduation_System.entity.User;

public interface UserService extends UserDetailsService {
    User loadUserByUsername(String username);
}
