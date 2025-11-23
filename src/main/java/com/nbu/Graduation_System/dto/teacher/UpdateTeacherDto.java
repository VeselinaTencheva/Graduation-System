package com.nbu.Graduation_System.dto.teacher;

import com.nbu.Graduation_System.entity.enums.UserRoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeacherDto {

    @Size(min = 2, max = 100)
    private String name;

    @Email
    private String email;

    // optional – if null/empty, keep old password
    @Size(min = 6, max = 100)
    private String password;

    // optional (you can keep it fixed as TEACHER if you prefer)
    private UserRoleType role;

    // optional – change department if provided
    private Long departmentId;

    // optional – change academic title if provided
    private String academicTitle;
}
