package ru.practicum.shareit.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.dto.item.ItemCreateDto;
import ru.practicum.shareit.dto.item.ItemDto;
import ru.practicum.shareit.dto.item.ItemUpdateDto;
import ru.practicum.shareit.service.item.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Validated
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDto create(@RequestHeader("X-Sharer-User-Id") @Positive Long userId,
                          @RequestBody @Valid ItemCreateDto dto) {
        return itemService.create(dto, userId);
    }

    @GetMapping("/{id}")
    public ItemDto getById(@PathVariable @Positive Long id) {
        return itemService.findById(id);
    }

    @PatchMapping("/{id}")
    public ItemDto update(@PathVariable @Positive Long id, @RequestBody @Valid ItemUpdateDto dto) {
        return itemService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Long id) {
        itemService.delete(id);

    }
    @GetMapping
    public List<ItemDto> getAll() {
        return itemService.findAll();
    }
    @GetMapping("/users/{ownerId}/items")
    public List<ItemDto> getByOwner(@PathVariable @Positive Long ownerId,
                                    @RequestParam(defaultValue = "0") int from,
                                    @RequestParam(defaultValue = "10") int size) {
        return itemService.findAllByOwnerId(ownerId, from, size);
    }


}
