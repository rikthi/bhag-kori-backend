package dev.rikthipranadhik.bhagkori.service;

import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.User;

public interface RoomService {
    Room createRoom(Room room);
    Room updateRoom(Room room);
    void deleteRoom(Room room);
    Room addMember(Room room, User member);
    Room removeMember(Room room, User member);
}
