package ru.practicum.shareit.dto.offer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.practicum.shareit.model.OfferStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferUpdateDto {
    @NotNull
    private OfferStatus status;

    @Size(max = 10_000)
    private String comment;
}