package ru.practicum.shareit.dto.item;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ItemUpdateDto {
    @Size(max = 100)
    private String name;
    @Size(max = 500)
    private String description;
    private Boolean available;

}
