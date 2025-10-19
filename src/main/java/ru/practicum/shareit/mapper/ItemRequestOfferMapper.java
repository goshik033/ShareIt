package ru.practicum.shareit.mapper;


import ru.practicum.shareit.dto.Offer.OfferCreateDto;
import ru.practicum.shareit.dto.Offer.OfferDto;
import ru.practicum.shareit.model.Item;
import ru.practicum.shareit.model.ItemRequest;
import ru.practicum.shareit.model.Offer;
import ru.practicum.shareit.model.User;

public final class ItemRequestOfferMapper {
    private ItemRequestOfferMapper() {}

    public static Offer toEntity(OfferCreateDto dto, ItemRequest request, Item item, User addedBy) {
        Offer e = new Offer();
        e.setRequest(request);
        e.setItem(item);
        e.setAddedBy(addedBy);
        e.setComment(dto.getComment());
        return e;
    }

    public static OfferDto toDto(Offer e) {
        return OfferDto.builder()
                .id(e.getId())
                .requestId(e.getRequest() != null ? e.getRequest().getId() : null)
                .itemId(e.getItem() != null ? e.getItem().getId() : null)
                .itemName(e.getItem() != null ? e.getItem().getName() : null)
                .addedById(e.getAddedBy() != null ? e.getAddedBy().getId() : null)
                .addedAt(e.getAddedAt())
                .comment(e.getComment())
                .offerStatus(e.getOfferStatus())
                .build();
    }
}