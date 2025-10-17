package dev.rikthipranadhik.bhagkori.domain.mapper;

import dev.rikthipranadhik.bhagkori.domain.dto.UserDto;
import dev.rikthipranadhik.bhagkori.domain.entity.User;

public interface UserMapper {
    UserDto toDto (User user);
    UserDto toDtoSecure (User user);
    User fromDto(UserDto userDto);
}
