package com.nbu.Graduation_System.viewmodel.thesis_review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CreateThesisReviewViewModel {

    @NotBlank(message = "Comments cannot be null")
    private String comments;

    @NotNull(message = "Review type cannot be null")
    private Boolean positive;
}
