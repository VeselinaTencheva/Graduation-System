package com.nbu.Graduation_System.service.user;

import com.nbu.Graduation_System.dto.UserDto;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto save(UserDto user);
    Optional<UserDto> findById(Long id);
    List<UserDto> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
