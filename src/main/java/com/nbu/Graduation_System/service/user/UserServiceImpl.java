package com.nbu.Graduation_System.service.user;


import com.nbu.Graduation_System.dto.user.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nbu.Graduation_System.entity.User;
import com.nbu.Graduation_System.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        // In a real app, you'd handle password encoding and roles more robustly
        if (userDto.getId() == null) { // Assuming new user if id is null
            user.setPassword(passwordEncoder.encode("defaultPassword")); // Set a default password
        }
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }
}
