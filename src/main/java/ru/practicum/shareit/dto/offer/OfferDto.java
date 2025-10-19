package ru.practicum.shareit.dto.Offer;

import lombok.*;
import ru.practicum.shareit.model.OfferStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferDto {
    private Long id;
    private Long requestId;
    private Long itemId;
    private String itemName;
    private Long addedById;
    private LocalDateTime addedAt;
    private String comment;
    private OfferStatus offerStatus;
}