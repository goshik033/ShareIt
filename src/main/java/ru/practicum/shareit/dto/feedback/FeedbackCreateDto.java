package ru.practicum.shareit.dto.feedback;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackCreateDto {
    @Size(max = 500)
    private String description;
    @NotNull
    @Min(1)
    @Max(5)
    private Short rating;
}
