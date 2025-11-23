package com.nbu.Graduation_System.controller.api;

import com.nbu.Graduation_System.dto.user.CreateUserDto;
import com.nbu.Graduation_System.dto.user.UpdateUserDto;
import com.nbu.Graduation_System.dto.user.UserDto;
import com.nbu.Graduation_System.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/by-email")
    public ResponseEntity<UserDto> getByEmail(@RequestParam String email) {
        // service throws if not found; you can wrap in try/catch if you prefer 404
        UserDto dto = userService.findByEmail(email);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto request) {
        UserDto created = userService.createUser(request);
        return ResponseEntity
                .created(URI.create("/api/users/" + created.getId()))
                .body(created);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserDto request
    ) {
        UserDto updated = userService.updateUser(id, request);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
