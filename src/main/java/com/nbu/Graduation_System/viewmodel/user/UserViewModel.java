package com.nbu.Graduation_System.viewmodel.user;

import com.nbu.Graduation_System.entity.enums.UserRoleType;
import lombok.Data;

@Data
public class UserViewModel {
    private Long id;
    private String name;
    private String email;
    private UserRoleType role;
}
