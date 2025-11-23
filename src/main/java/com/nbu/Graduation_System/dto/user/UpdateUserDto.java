package com.nbu.Graduation_System.dto.user;

import com.nbu.Graduation_System.entity.enums.UserRoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
        @Size(min = 2, max = 100)
       private String name;     

        @Email
        private String email;

        // optional – if null/empty, keep old password
        @Size(min = 6, max = 100)
        String password;

        UserRoleType role;
}
