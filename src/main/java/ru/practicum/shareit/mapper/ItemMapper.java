package ru.practicum.shareit.mapper;

import ru.practicum.shareit.dto.item.ItemCreateDto;
import ru.practicum.shareit.dto.item.ItemDto;
import ru.practicum.shareit.model.Item;
import ru.practicum.shareit.model.User;

public final class ItemMapper {
    private ItemMapper() {}
    public static Item toEntity(ItemCreateDto itemCreateDto, User user) {
        return Item.builder()
                .name(itemCreateDto.getName())
                .description(itemCreateDto.getDescription())
                .available(itemCreateDto.getAvailable())
                .owner(user)
                .build();
    }

    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .ownerId(item.getOwner() != null ? item.getOwner().getId() : null)
                .build();
    }
}
