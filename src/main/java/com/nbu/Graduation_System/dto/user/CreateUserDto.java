package com.nbu.Graduation_System.dto.user;

import com.nbu.Graduation_System.entity.enums.UserRoleType;
import lombok.Data;

@Data
public class CreateUserDto {
    private String name;
    private String email;
    private String password;
    private UserRoleType role;
}
