package ru.practicum.shareit.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.practicum.shareit.dto.UserCreateDto;
import ru.practicum.shareit.dto.UserDto;
import ru.practicum.shareit.dto.UserUpdateDto;


public interface UserService {
    UserDto create(UserCreateDto dto);
    UserDto getById(Long id);
    Page<UserDto> getAll(Pageable pageable);
    UserDto update(Long id, UserUpdateDto dto);
    void delete(Long id);
}