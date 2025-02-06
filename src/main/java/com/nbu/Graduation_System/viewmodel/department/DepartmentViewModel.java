package com.nbu.Graduation_System.viewmodel.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.nbu.Graduation_System.entity.enums.DepartmentType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DepartmentViewModel {

    private Long id;

    @NotNull
    private DepartmentType type;

    // @Size(min = 3, max = 50)
    @NotBlank
    private String description;

    // @Size(min = 3, max = 50)
    @NotBlank
    private String contactEmail;
}
