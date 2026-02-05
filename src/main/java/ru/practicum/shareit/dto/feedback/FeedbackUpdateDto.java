package ru.practicum.shareit.dto.feedback;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackUpdateDto {
    @Size(max = 500)
    private String description;
    @Min(1)
    @Max(5)
    private Short rating;
}
