package com.nbu.Graduation_System.viewmodel.thesis_application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateThesisApplicationViewModel {
    
    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters")
    private String title;

    @NotBlank(message = "Objective is required")
    @Size(min = 20, max = 300, message = "Objective must be between 20 and 300 characters")
    private String objective;

    @NotBlank(message = "Tasks are required")
    @Size(min = 20, max = 300, message = "Tasks must be between 20 and 300 characters")
    private String tasks;

    @NotBlank(message = "Technologies are required")
    @Size(min = 20, max = 300, message = "Technologies must be between 20 and 300 characters")
    private String technologies;

    @NotNull(message = "Student is required")
    private Long studentId;
}
