package com.nbu.Graduation_System.service.user;

import com.nbu.Graduation_System.dto.user.CreateUserDto;
import com.nbu.Graduation_System.dto.user.UpdateUserDto;
import com.nbu.Graduation_System.dto.user.UserDto;
import com.nbu.Graduation_System.entity.User;
import com.nbu.Graduation_System.repository.UserRepository;
import com.nbu.Graduation_System.util.MapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MapperUtil mapperUtil;

    // 🔐 Used by Spring Security
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }

    @Override
    public List<UserDto> findAll() {
        return mapperUtil.mapList(userRepository.findAll(), UserDto.class);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email=" + email + " not found!"));

        return mapperUtil.getModelMapper().map(user, UserDto.class);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id=" + id + " not found!"));

        return mapperUtil.getModelMapper().map(user, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto createUser(CreateUserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists: " + dto.getEmail());
        }

        User user = mapperUtil.getModelMapper().map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // if you want default flags:
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        user = userRepository.save(user);
        return mapperUtil.getModelMapper().map(user, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UpdateUserDto dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id=" + id));

        if (dto.getName() != null) {
            existing.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            existing.setEmail(dto.getEmail());
        }
        if (dto.getRole() != null) {
            existing.setRole(dto.getRole());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        existing = userRepository.save(existing);
        return mapperUtil.getModelMapper().map(existing, UserDto.class);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id=" + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
