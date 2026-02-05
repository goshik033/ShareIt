package ru.practicum.shareit.dto.itemRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRequestCreateDto {
    @NotBlank
    @Size(max = 200)
    private String title;

    @Size(max = 10_000)
    private String description;
}