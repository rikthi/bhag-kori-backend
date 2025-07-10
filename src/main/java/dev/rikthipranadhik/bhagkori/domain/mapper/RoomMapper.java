package dev.rikthipranadhik.bhagkori.domain.mapper;

import dev.rikthipranadhik.bhagkori.domain.dto.RoomDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Room;

public interface RoomMapper {
    RoomDto toDto(Room room);
    Room fromDto(RoomDto roomDto);
}
