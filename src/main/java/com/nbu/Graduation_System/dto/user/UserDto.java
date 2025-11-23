package com.nbu.Graduation_System.dto.user;
import com.nbu.Graduation_System.entity.enums.UserRoleType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private UserRoleType role;
}
