package ru.practicum.shareit.service.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.practicum.shareit.dto.user.UserCreateDto;
import ru.practicum.shareit.dto.user.UserDto;
import ru.practicum.shareit.dto.user.UserUpdateDto;


public interface UserService {
    UserDto create(UserCreateDto dto);
    UserDto findById(Long id);
    Page<UserDto> findAll(Pageable pageable);
    UserDto update(Long id, UserUpdateDto dto);
    void delete(Long id);
}