package ru.practicum.shareit.mapper;



import ru.practicum.shareit.dto.UserCreateDto;
import ru.practicum.shareit.dto.UserDto;
import ru.practicum.shareit.model.User;


public final class UserMapper {
    private UserMapper() {}

    public static User toEntity(UserCreateDto dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}