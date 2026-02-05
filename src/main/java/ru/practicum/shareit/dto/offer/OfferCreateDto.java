package ru.practicum.shareit.dto.offer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferCreateDto {
    @NotNull
    private Long requestId;

    @NotNull
    private Long itemId;

    @Size(max = 10_000)
    private String comment;
}