package ru.practicum.shareit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.shareit.dto.ItemCreateDto;

@Controller
@RequestMapping("/items")
public class ItemController {
    @PostMapping
    public ItemCreateDto addItem(@RequestHeader("X-Sharer-User-Id") Long userId, @RequestBody ItemCreateDto item){

        return null;
    }


}
