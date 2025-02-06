package com.nbu.Graduation_System.viewmodel.thesis;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateThesisViewModel {
    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 20, message = "Title must be between 5 and 20 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 5, max = 100, message = "Content must be between 5 and 100 characters")
    private String content;
}
