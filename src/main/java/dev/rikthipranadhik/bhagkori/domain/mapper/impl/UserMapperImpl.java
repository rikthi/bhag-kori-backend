package dev.rikthipranadhik.bhagkori.domain.mapper.impl;

import dev.rikthipranadhik.bhagkori.domain.dto.UserDto;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{
    @Override
    public User fromDto(UserDto userDto) {
        return new User(
                userDto.id(),
                userDto.email(),
                userDto.phoneNumber(),
                userDto.name(),
                userDto.password(),
                null
        );
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getName(),
                user.getPassword()
        );
    }
}
