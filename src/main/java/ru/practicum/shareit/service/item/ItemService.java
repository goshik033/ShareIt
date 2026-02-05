package ru.practicum.shareit.service.item;

import ru.practicum.shareit.dto.item.ItemCreateDto;
import ru.practicum.shareit.dto.item.ItemDto;
import ru.practicum.shareit.dto.item.ItemUpdateDto;
import ru.practicum.shareit.model.Item;

import java.util.List;

public interface ItemService {
    ItemDto create(ItemCreateDto dto, Long userId);
    ItemDto update(ItemUpdateDto dto, Long id);
    void delete(Long id) ;
    ItemDto findById(Long id);
    List<ItemDto> findAll();
    List<ItemDto> findAllByOwnerId(Long id, int from, int size);



}
