package com.nbu.Graduation_System.viewmodel.thesis;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateThesisViewModel {

    @NotBlank(message = "Content is required")
    // @Size(min = 5, max = 100, message = "Content must be between 5 and 100 characters")
    private String content;
    
    private Long applicationId;
}
