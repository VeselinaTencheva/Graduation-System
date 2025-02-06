package com.nbu.Graduation_System.viewmodel.user;

import com.nbu.Graduation_System.entity.enums.UserRoleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserViewModel {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private UserRoleType role;
}