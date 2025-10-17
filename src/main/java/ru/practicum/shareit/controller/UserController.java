package ru.practicum.shareit.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.dto.UserCreateDto;
import ru.practicum.shareit.dto.UserDto;
import ru.practicum.shareit.dto.UserUpdateDto;
import ru.practicum.shareit.service.UserService;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid UserCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public Page<UserDto> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @PatchMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody @Valid UserUpdateDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
