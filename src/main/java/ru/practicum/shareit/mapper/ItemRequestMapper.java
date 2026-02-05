package ru.practicum.shareit.mapper;


import ru.practicum.shareit.dto.itemRequest.ItemRequestCreateDto;
import ru.practicum.shareit.dto.itemRequest.ItemRequestDto;
import ru.practicum.shareit.dto.itemRequest.ItemRequestUpdateDto;
import ru.practicum.shareit.model.ItemRequest;
import ru.practicum.shareit.model.User;

import java.util.Collections;

public final class ItemRequestMapper {
    private ItemRequestMapper() {}

    public static ItemRequest toEntity(ItemRequestCreateDto dto, User requester) {
        ItemRequest e = new ItemRequest();
        e.setRequester(requester);
        e.setTitle(dto.getTitle());
        e.setDescription(dto.getDescription());
        e.setStatus("OPEN");
        e.setOffers(Collections.emptySet());
        return e;
    }

    public static void updateEntity(ItemRequestUpdateDto dto, ItemRequest e) {
        if (dto.getTitle() != null) e.setTitle(dto.getTitle());
        if (dto.getDescription() != null) e.setDescription(dto.getDescription());
        if (dto.getStatus() != null) e.setStatus(dto.getStatus());
    }

    public static ItemRequestDto toDto(ItemRequest e) {
        return ItemRequestDto.builder()
                .id(e.getId())
                .requesterId(e.getRequester() != null ? e.getRequester().getId() : null)
                .title(e.getTitle())
                .description(e.getDescription())
                .status(e.getStatus())
                .createdAt(e.getCreatedAt())
                .build();
    }
}