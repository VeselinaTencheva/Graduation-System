package com.nbu.Graduation_System.service.user;

import com.nbu.Graduation_System.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.nbu.Graduation_System.entity.User;

import java.util.List;

import java.util.Optional;

public interface UserService extends UserDetailsService {
        User loadUserByUsername(String username);
    List<User> findAll();

    Optional<User> findById(Long id);

    UserDto save(UserDto userDto);

    boolean existsById(Long id);

    void deleteById(Long id);
}
