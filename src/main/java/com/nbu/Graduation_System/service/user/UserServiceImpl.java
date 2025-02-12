package com.nbu.Graduation_System.service.user;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.nbu.Graduation_System.entity.User;
import com.nbu.Graduation_System.repository.UserRepository;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }
}
