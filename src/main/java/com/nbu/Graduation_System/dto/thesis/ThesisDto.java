package com.nbu.Graduation_System.dto.thesis;

import com.nbu.Graduation_System.entity.ThesisApplication;
import com.nbu.Graduation_System.entity.ThesisDefense;
import com.nbu.Graduation_System.entity.ThesisReview;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ThesisDto {

    private Long id;

    @NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String title;

    @NotBlank
    @Size(min = 5, max = 100, message="Min 5, Max 100")
    private String content;

    private ThesisApplication thesisApplication;
    
    private ThesisReview review;
    
    private ThesisDefense defense;
}
