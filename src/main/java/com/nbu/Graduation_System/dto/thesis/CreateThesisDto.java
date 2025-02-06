package com.nbu.Graduation_System.dto.thesis;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateThesisDto {
    @NotBlank
    // @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String title;

    @NotBlank
    // @Size(min = 5, max = 100, message="Min 5, Max 100")
    private String content;
}
