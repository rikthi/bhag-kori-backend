package dev.rikthipranadhik.bhagkori.domain.mapper.impl;

import dev.rikthipranadhik.bhagkori.domain.dto.RoomDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.mapper.RoomMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room fromDto(RoomDto roomDto) {
        return new Room(
                roomDto.id(),
                roomDto.name(),
                roomDto.description(),
                roomDto.createTime(),
                new User(),
                null
        );
    }

    @Override
    public RoomDto toDto(Room room) {
        return new RoomDto(
                room.getId(),
                room.getName(),
                room.getDescription(),
                room.getCreateTime(),
                room.getCreator().getId(),
                room.getMembers().stream().map(User::getId).collect(Collectors.toSet())
        );
    }
}
