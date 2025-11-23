package com.nbu.Graduation_System.service.user;

import com.nbu.Graduation_System.dto.user.CreateUserDto;
import com.nbu.Graduation_System.dto.user.UpdateUserDto;
import com.nbu.Graduation_System.dto.user.UserDto;
import com.nbu.Graduation_System.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    List<UserDto> findAll();

    UserDto findByEmail(String email);

    UserDto findById(Long id);

    UserDto createUser(CreateUserDto dto);

    UserDto updateUser(Long id, UpdateUserDto dto);

    void deleteUser(Long id);

    boolean existsByEmail(String email);
}
